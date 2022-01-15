package com.skx.tomike.tank.widget.view

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.skx.common.utils.dip2px
import com.skx.tomike.tank.R
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

class DynamicMsgLayout : ViewGroup {

    /*
    1.自动取消 -> 3s
    初步想法：不打算使用绝对时间，通过每秒循环来获取是否有到时间的消息来处理倒计时。
            尝试通过两个消息之间的时间差来处理。把所有的消息放入一个队列中 -> 先进先出....
            但是发现两个问题：① 手动取消中间的某个消息队列无法处理 ② 手动取消某个消息倒计时错乱
            不过有一点是肯定的，整个view 只能有一个倒计时，绝对不会为每个child 分配一个倒计时

    2.消息绘制
    3.消息位置
     */

    // 消息缓存池
    private val mPool: Stack<Message> = Stack()

    // 当前展示的消息队列
    private val messages: LinkedList<Message> = LinkedList()
    private var mBucketId = 0

    // 桶数组，包含所有的桶对象
    private val mBuckets: MutableList<Bucket?> = mutableListOf()

    // 消息view 对 桶的映射关系
    private val msg2bucket: HashMap<Message, Bucket> = HashMap()

    // 倒计时
    private val mTimer = Timer()
    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: android.os.Message) {
            super.handleMessage(msg)
            when (msg.what) {
                0 -> {
                    if (messages.size <= 0) {
                        return
                    }
                    var i = 0
                    while (i >= 0 && i < messages.size) {
                        Log.e(
                            "DynamicMsgLayout",
                            "countdown ->index:${i} size:${messages.size}"
                        )
                        val message = messages[i]
                        val diff = System.currentTimeMillis() - message.beginTime
                        if (diff > 3000) {
                            val bucket = msg2bucket[message]
                            bucket?.messages?.poll()?.run {
                                // 移除msg view
                                removeMsg(this)
                            }
                        } else {
                            break
                        }
                        i++
                    }
                }
            }
        }
    }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initCountdownTime()
        initBucketXY()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        initCountdownTime()
    }

    private fun initCountdownTime() {
        mTimer.scheduleAtFixedRate(0, 200) {
            mHandler.sendEmptyMessage(0)
        }
    }

    fun initBucketLocByRv(rv: RecyclerView) {
        mBuckets.clear()
        val childCount: Int = rv.adapter?.itemCount ?: 0
        for (i in 0 until childCount) {
//            val viewholder = rv.findViewHolderForAdapterPosition(i)
            val view = rv.getChildAt(i)
            view?.run {
                val bucket = Bucket()
                bucket.x = (right - left) / 2
                bucket.y = top
                mBuckets.add(i, bucket)
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            // 测量子view 的宽高
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
        }
        setMeasuredDimension(widthSize, heightSize)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until messages.size) {
            val message = messages[i]
            val child = message.view
            // 跳过View.GONE的子View
            if (child == null || GONE == child.visibility) {
                continue
            }
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight

            val bucket = msg2bucket[message]

            val left = bucket?.run {
                x - childWidth / 2
            } ?: run {
                0
            }
            val top = bucket?.run {
                y - childHeight
            } ?: run {
                0
            }
            child.layout(left, top, left + childWidth, (bucket?.y ?: 0))
        }
    }

    fun sendMessage(bucketIndex: Int, msg: String) {
        Log.e("DynamicMsgLayout", "sendMessage -> START")
        if (TextUtils.isEmpty(msg)) {
            return
        }
        // 1.获取message 实例对象
        val message = getMessage().apply {
            this.text = msg
            this.beginTime = System.currentTimeMillis()
            this.view?.text = msg
        }

        // 2.获取消息所属的桶
        val bucketByIndex = getBucketByIndex(bucketIndex)

        // 3.桶内已经有消息，先出队
        bucketByIndex.messages.takeIf {
            it.size > 0
        }?.run {
            this.poll()?.run {
                // 移除msg view
                removeMsg(this)
            }
        }
        Log.e("DynamicMsgLayout", "sendMessage -> after remote msg -> ${messages.size}")


        // 4.将新消息view添加到桶里面
        messages.add(message)
        bucketByIndex.messages.add(message)
        msg2bucket[message] = bucketByIndex
        // 5.添加view
        message.view?.run {
            addView(
                this,
                MarginLayoutParams(MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT)
            )
        }
        Log.e("DynamicMsgLayout", "sendMessage -> after add new -> ${messages.size}")
    }

    /**
     * 获取message view。优先从缓存池中加载，如果缓存池中没有空余的，则创建
     */
    private fun getMessage(): Message {
        var msg: Message? = null
        // 1.优先从缓存里添加
        if (mPool.size != 0) {
            msg = mPool.pop()
        }
        // 2.缓存中没有可用的->创建新的
        if (msg == null) {
            msg = Message()
            msg.view = TextView(context)
                .apply {
                    setPadding(30)
                    ellipsize = TextUtils.TruncateAt.END
                    maxLines = 2
                    maxWidth = dip2px(context, 150f)
                    setBackgroundResource(R.drawable.rectangle_solid_ffdee9_str_corner_5)
                }
        }
        return msg
    }

    private fun removeMsg(msg: Message) {
        // 移除显示消息内容的 view
        msg.view?.run {
            removeView(this)
        }
        // 从消息队里移除消息，移除和桶的映射关系
        messages.remove(msg)
        msg2bucket.remove(msg)
        // 重置数据,添加到缓存池中
        msg.run {
            this.text = ""
            this.beginTime = -1L
        }
        mPool.push(msg)
    }

    /**
     * 根据索引获取一个桶，如果该索引没有对应的桶对象，则创建一个桶对象并返回。
     */
    private fun getBucketByIndex(bucketIndex: Int): Bucket {
        var bucket: Bucket? = null
        if (bucketIndex >= 0 && bucketIndex < mBuckets.size) {
            bucket = mBuckets[bucketIndex]
        }
        if (bucket == null) {
            bucket = Bucket()
//            bucket.id = mBucketId
            mBuckets.add(bucketIndex, bucket)
            mBucketId++
        }
        return bucket
    }

    /**
     * 消息
     */
    class Message {
        var text: String = ""
        var beginTime: Long = 0
        var view: TextView? = null
    }

    class Bucket() {
        var x: Int = 0
        var y: Int = 0
        var messages: Queue<Message> = LinkedList()
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}