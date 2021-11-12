package com.skx.tomike.tank.widget.activity

import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTER_GROUP
import com.skx.tomike.tank.ROUTE_PATH_RECYCLER_SCROLL2POS
import com.skx.tomike.tank.widget.adapter.ItemAnimatorAdapter
import java.util.*


/**
 * 描述 : RecyclerView 滑动到指定位置
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/5/30 5:01 PM
 */
@Route(path = ROUTE_PATH_RECYCLER_SCROLL2POS, group = ROUTER_GROUP)
class RecyclerViewScrollToPositionActivity : SkxBaseActivity<BaseViewModel?>(),
    RadioGroup.OnCheckedChangeListener {

    private var mTvTargetPos: TextView? = null
    private var mRecyclerView: RecyclerView? = null
    private var centerLayoutManager: CenterLayoutManager? = null

    private var mTargetPosition: Int = 0

    private val mContentList: MutableList<String> = LinkedList()

    override fun initParams() {
        var i = 0
        val j = 50
        while (i < j) {
            mContentList.add("第" + i + "个")
            i++
        }
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("RecyclerView 滑动到指定位置").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_recyclerview_scroll_to_position
    }

    override fun initView() {
        findViewById<RadioGroup>(R.id.btn_recyclerviewScrollToPosition_configGroup).setOnCheckedChangeListener(
            this
        )
        mTvTargetPos = findViewById(R.id.tv_recyclerviewScrollToPosition_targetPos)

        mRecyclerView = findViewById(R.id.rv_recyclerviewScrollTpPosition_show)
//        centerLayoutManager = CenterLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mRecyclerView?.layoutManager =
            LinearLayoutManager(mActivity, RecyclerView.HORIZONTAL, false)
        mRecyclerView?.adapter = ItemAnimatorAdapter(mContentList)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(mRecyclerView)

        findViewById<TextView>(R.id.tv_recyclerviewScrollToPosition_startBtn).setOnClickListener {
//            mRecyclerView?.scrollToPosition(mTargetPosition)
//            mRecyclerView?.smoothScrollToPosition(mTargetPosition)

            val findSnapView = snapHelper.findSnapView(mRecyclerView?.layoutManager)
            var offset = 0
            findSnapView?.apply {
                Log.e(TAG, "child - view width:" + layoutParams.width)
                val minus = mRecyclerView?.layoutParams?.width?.minus(layoutParams.width)
                Log.e(TAG, "child - minus:$minus")
                offset = minus?.div(2)!!
                Log.e(TAG, "child - offset:$offset")

            }

            if (mRecyclerView?.layoutManager is LinearLayoutManager) {
                (mRecyclerView?.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                    mTargetPosition,
                    offset
                )
            }

        }

        findViewById<TextView>(R.id.iv_recyclerviewScrollToPosition_moreBtn).setOnClickListener {
            mTargetPosition++
            mTvTargetPos?.text = mTargetPosition.toString()
        }
        findViewById<TextView>(R.id.iv_recyclerviewScrollToPosition_lessBtn).setOnClickListener {
            mTargetPosition--
            mTvTargetPos?.text = mTargetPosition.toString()
        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.btn_recyclerviewScrollToPosition_horizontal -> {
                mRecyclerView?.apply {
                    val layoutManager =
                        LinearLayoutManager(mActivity, RecyclerView.HORIZONTAL, false)
                    mRecyclerView?.layoutManager = layoutManager
                    mRecyclerView?.adapter = ItemAnimatorAdapter(mContentList)
                }
            }
            R.id.btn_recyclerviewScrollToPosition_verticalFixHeight -> {
                mRecyclerView?.apply {
                    val layoutManager = LinearLayoutManager(mActivity, RecyclerView.VERTICAL, false)
                    mRecyclerView?.layoutManager = layoutManager
                    mRecyclerView?.adapter = ItemAnimatorAdapter(mContentList)
                }
            }
        }
    }

    class CenterLayoutManager(context: Context?, orientation: Int, reverseLayout: Boolean) :
        LinearLayoutManager(context, orientation, reverseLayout) {

        override fun smoothScrollToPosition(
            recyclerView: RecyclerView,
            state: RecyclerView.State,
            position: Int
        ) {
            val smoothScroller: RecyclerView.SmoothScroller =
                CenterSmoothScroller(recyclerView.context)
            smoothScroller.targetPosition = position
            startSmoothScroll(smoothScroller)
        }

        private class CenterSmoothScroller(context: Context?) : LinearSmoothScroller(context) {

            override fun calculateDtToFit(
                viewStart: Int,
                viewEnd: Int,
                boxStart: Int,
                boxEnd: Int,
                snapPreference: Int
            ): Int {
                return boxStart + (boxEnd - boxStart) / 2 - (viewStart + (viewEnd - viewStart) / 2)
            }

            override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                return 100f / displayMetrics.densityDpi
            }
        }
    }
}