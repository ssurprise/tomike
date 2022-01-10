package com.skx.tomike.tank.widget.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.setPadding
import com.skx.tomike.tank.R
import java.util.*
import kotlin.collections.HashMap

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

    private var autoMiss = true

    // view缓存池
    private val mPool: Stack<TextView> = Stack()
    private var mBucketId = 0

    // 桶数组，包含所有的桶对象
    private val mBuckets: Array<Bucket?> = arrayOf(
        Bucket(0),
        Bucket(1),
        Bucket(2),
        Bucket(3),
        Bucket(4),
        Bucket(5),
        Bucket(6)
    )

    // 消息view 对 桶的映射关系
    private val msg2bucket: HashMap<View, Bucket> = HashMap()

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initBucketXY()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        initBucketXY()
    }

    private fun initBucketXY() {
        mBuckets[0]?.apply {
            x = 30
            y = 100
        }
        mBuckets[1]?.apply {
            x = 300
            y = 100
        }
        mBuckets[2]?.apply {
            x = 700
            y = 100
        }
        mBuckets[3]?.apply {
            x = 30
            y = 300
        }
        mBuckets[4]?.apply {
            x = 300
            y = 300
        }
        mBuckets[5]?.apply {
            x = 700
            y = 300
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
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            //跳过View.GONE的子View
            if (child.visibility == GONE) {
                continue
            }
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight

            val bucket = msg2bucket[child]

            child.layout(
                bucket?.x ?: 0,
                bucket?.y ?: 0,
                (bucket?.x ?: 0) + childWidth,
                (bucket?.y ?: 0) + childHeight
            )
        }
    }

    fun sendMessage(bucketIndex: Int, msg: String) {
        var tv: TextView? = null
        // 1.优先从缓存里添加
        if (mPool.size != 0) {
            tv = mPool.pop()
        }
        if (tv == null) {
            tv = TextView(context)
                .apply {
                    setPadding(30)
                    setBackgroundResource(R.drawable.rectangle_solid_ffdee9_str_corner_5)
                }
        }
        tv.text = msg

        // 2.获取所在的桶
        val bucketByIndex = getBucketByIndex(bucketIndex)

        // 3.桶内已经有消息，先出队
        if (bucketByIndex.messages.size > 0) {
            val lastView = bucketByIndex.messages.poll()
            removeView(lastView)
            // 添加到缓存池中
            mPool.push(lastView)
        }

        // 4.将新消息view添加到桶里面
        bucketByIndex.messages.add(tv)
        msg2bucket[tv] = bucketByIndex
        // 添加view
        addView(
            tv,
            MarginLayoutParams(MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT)
        )
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
            bucket.id = mBucketId
            mBuckets[bucketIndex] = bucket
            mBucketId++
        }
        return bucket
    }

    /**
     * 消息
     */
    class Message {
        var msg: String = ""
        var beginTime: Long = 0
        var bucketId: Int = -1
        var view: View? = null
    }

    class Bucket(var id: Int = 0) {
        var x: Int = 0
        var y: Int = 0
        var messages: Queue<TextView> = LinkedList()
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}