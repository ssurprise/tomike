package com.skx.tomike.tank.widget.view

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.skx.common.utils.dip2px
import com.skx.tomike.tank.R
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate


/**
 * 描述 : 动态消息展示Layout
 * <p>
 * 1.自动取消 -> 3s
 * 初步想法：不打算使用绝对时间，通过每秒循环来获取是否有到时间的消息来处理倒计时。
 * 尝试通过两个消息之间的时间差来处理。把所有的消息放入一个队列中 -> 先进先出....
 * 但是发现两个问题：① 手动取消中间的某个消息队列无法处理 ② 手动取消某个消息倒计时错乱
 * 不过有一点是肯定的，整个view 只能有一个倒计时，绝对不会为每个child 分配一个倒计时。
 *
 * 最终想法：使用Timer 进行倒计时，配合handler 进行显示控制。每个消息在创建或者使用时会配置一个当前时间。出于性能考虑，
 * 倒计时每100ms执行一次。在倒计时轮询时，检查每个消息的时间，因为是用的队列，所以当一个消息未达到指定显示时长时，
 * 后续的消息也必然达不到显示时长，也就不用去做判断了。缺点：因为倒计时每100ms执行一次，所以会存在一个临界值，
 * 导致消息view 多显示100ms，不过可以接受。
 * </p>

 * <p>
 * 2.消息绘制
 * 消息依附于桶，所以在绘制消息view 之前，需要先确定桶的位置。以桶的位置作为锚点，然后绘制下消息view 的范围。
 * </p>
 * <p>
 * 3.消息位置
 * 目前只提供了一种确定桶位置的方法，{@link #initBucketLocByRv(RecyclerView)}
 * </p>
 *
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2022/1/10 4:00 下午
 */
class DynamicMsgLayout : ViewGroup {

    companion object {
        private const val TAG = "DynamicMsgLayout"
        private const val DISPLAY_TIME = 3000
    }

    // 消息缓存池
    private val mPool: Stack<Message> = Stack()

    // 当前展示的消息队列，用于绘制使用
    private val messages: LinkedList<Message> = LinkedList()

    // 桶数组，包含所有的桶对象
    private val mBuckets: MutableList<Bucket?> = mutableListOf()

    // 消息view 对 桶的映射关系
    private val msg2bucket: HashMap<Message, Bucket> = HashMap()
    private var leftOffset = 0
    private var rightOffset = 0

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
                        val message = messages[i]
                        val diff = System.currentTimeMillis() - message.beginTime
                        if (diff >= DISPLAY_TIME) {
                            Log.e(
                                TAG, "倒计时->size:${messages.size} index:${i} diff:${diff} 已到显示时长，删除"
                            )
                            val bucket = msg2bucket[message]
                            bucket?.messages?.poll()?.run {
                                // 移除msg view
                                removeMsg(this)
                            }
                        } else {
                            Log.e(TAG, "倒计时 ->index:${i} diff:${diff} 未到显示时长，继续显示")
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
    )

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        initCountdownTime()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mTimer.cancel()
    }

    private fun initCountdownTime() {
        mTimer.scheduleAtFixedRate(0, 100) {
            mHandler.sendEmptyMessage(0)
        }
    }

    /**
     * 根据recyclerview 的child view 位置初始化桶的坐标
     */
    fun initBucketLocByRv(rv: RecyclerView, xOffset: Int = 0, yOffset: Int = 0) {
        mBuckets.clear()
        Log.e(TAG, "initBucketLocByRv 初始化桶坐标-start")
        val childCount: Int = rv.adapter?.itemCount ?: 0
        for (i in 0 until childCount) {
            val view = rv.getChildAt(i)
            view?.run {
                val bucket = Bucket()
                bucket.x = (right + left) / 2 + xOffset
                bucket.y = top + yOffset
                Log.e(TAG, "bucket:${i} x=${bucket.x} y=${bucket.y}")
                mBuckets.add(i, bucket)
            }
        }
        Log.e(TAG, "initBucketLocByRv 初始化桶坐标-end")
    }

    /**
     * 初始化一个桶的坐标
     */
    fun initBucketLoc(x: Int, y: Int) {
        mBuckets.clear()
        Log.e(TAG, "initBucketLoc 初始化桶坐标-start")
        val bucket = Bucket()
        bucket.x = x
        bucket.y = y
        Log.e(TAG, "bucket x=${bucket.x} y=${bucket.y}")
        mBuckets.add(bucket)
        Log.e(TAG, "initBucketLoc 初始化桶坐标-end")
    }

    /**
     * 设置左右偏移间距
     */
    fun setOffsetSpace(leftOffset: Int, rightOffset: Int) {
        this.leftOffset = leftOffset
        this.rightOffset = rightOffset
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
        val width = r - l
        // 从本地消息队列里取消息，消息中绑定有对应的view，确保只绘制在队列里的view。
        for (i in 0 until messages.size) {
            val message = messages[i]
            val child = message.view
            // 跳过View.GONE的子View
            if (child == null || GONE == child.visibility) {
                continue
            }

            // 从map中取对应的桶
            val bucket = msg2bucket[message]
            // 布局指示器view
            indicatorViewLayout(message.indicatorView, bucket)
            // 布局消息view
            msgViewLayout(child, bucket, width)
        }
    }

    private fun msgViewLayout(child: View, bucket: Bucket?, parentWidth: Int) {
        val childWidth = child.measuredWidth
        val childHeight = child.measuredHeight
        val left = bucket?.run {
            var tl = x - childWidth / 2
            if (tl < leftOffset) {
                //左边界越界的情况下，重置为最小允许范围
                tl = leftOffset
            }
            if (tl + childWidth > parentWidth - rightOffset) {
                //右边界越界的情况下，重置为最大允许范围
                tl = parentWidth - childWidth - rightOffset
            }
            tl
        } ?: run {
            0
        }
        val top = bucket?.run {
            y - childHeight
        } ?: run {
            0
        }
        // 消息view 的坐标跟随桶的坐标。
        child.layout(left, top, left + childWidth, (bucket?.y ?: 0))
    }

    private fun indicatorViewLayout(
        child: View?,
        bucket: Bucket?
    ) {
        if (bucket == null) return
        child?.run {
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight
            this.layout(
                bucket.x - childWidth / 2,
                bucket.y,
                bucket.x + childWidth / 2,
                bucket.y + childHeight
            )
        }
    }

    fun sendMessage(bucketIndex: Int, msg: String) {
        Log.e(TAG, "sendMessage -> START msg:${msg}")
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
        if (bucketByIndex == null) {
            Log.e(TAG, "sendMessage -> FAIL reason:没有找到对应的桶")
            return
        }

        // 3.桶内已经有消息，先出队
        bucketByIndex.messages.takeIf {
            it.size > 0
        }?.run {
            this.poll()?.run {
                // 移除msg view
                removeMsg(this)
                Log.e(TAG, "sendMessage -> remove previous msg -> ${messages.size}")
            }
        }
        Log.e(TAG, "sendMessage -> add new msg start-> ${messages.size}")

        // 4.将新消息view添加到桶里面
        messages.add(message)
        bucketByIndex.messages.add(message)
        msg2bucket[message] = bucketByIndex
        // 5.添加view
        message.indicatorView?.run {
            addView(
                this,
                MarginLayoutParams(60, 30)
            )
        }
        message.view?.run {
            addView(
                this,
                MarginLayoutParams(MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT)
            )
        }
        Log.e(TAG, "sendMessage -> add new msg end-> ${messages.size}")
        Log.e(TAG, "sendMessage -> END")
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
            msg.view = createTextView()
                .apply {
                    setOnClickListener {
                        val bucket = msg2bucket[msg]
                        bucket?.messages?.poll()?.run {
                            // 移除msg view
                            removeMsg(this)
                        }
                    }
                }
            msg.indicatorView = ImageView(context).apply {
                setImageResource(R.drawable.triangle_bottom_16dp_7d72ff)
            }
        }
        return msg
    }

    private fun createTextView(): TextView {
        return TextView(context).apply {
            setPadding(30)
            ellipsize = TextUtils.TruncateAt.END
            maxLines = 2
            maxWidth = dip2px(context, 150f)
            setBackgroundResource(R.drawable.rectangle_solid_ffdee9_str_corner_5)
        }
    }

    private fun removeMsg(msg: Message) {
        Log.e(TAG, "remove msg:${msg.text} hashCode:${msg.hashCode()}")

        // 移除显示消息内容的 view
        msg.view?.run {
            removeView(this)
        }
        msg.indicatorView?.run {
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
    private fun getBucketByIndex(bucketIndex: Int): Bucket? {
        var bucket: Bucket? = null
        if (bucketIndex >= 0 && bucketIndex < mBuckets.size) {
            // 获取指定位置的桶
            bucket = mBuckets[bucketIndex]
        }
        return bucket
    }

    /**
     * 消息
     */
    class Message {
        var text: String = ""

        // 消息开始展示时间
        var beginTime: Long = 0
        var view: TextView? = null
        var indicatorView: ImageView? = null
    }

    class Bucket {
        var x: Int = 0
        var y: Int = 0

        /**
         * 消息队列,这里当前其实用一个实例就够了，但是为了扩展方便做了预留，比如：上一个view 和下一个view 有过渡动画时
         * 这里如果只是一个变量那肯定就实现不了了。
         */
        var messages: Queue<Message> = LinkedList()
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}