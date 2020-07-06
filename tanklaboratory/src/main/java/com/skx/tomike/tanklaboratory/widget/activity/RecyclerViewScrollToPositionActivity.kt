package com.skx.tomike.tanklaboratory.widget.activity

import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skx.tomike.tanklaboratory.R
import com.skx.tomike.tanklaboratory.widget.adapter.ItemAnimatorAdapter
import com.skx.tomikecommonlibrary.base.BaseViewModel
import com.skx.tomikecommonlibrary.base.SkxBaseActivity
import com.skx.tomikecommonlibrary.base.TitleConfig
import java.util.*

/**
 * 描述 : RecyclerView 滑动到指定位置
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/5/30 5:01 PM
 */
class RecyclerViewScrollToPositionActivity : SkxBaseActivity<BaseViewModel?>(), RadioGroup.OnCheckedChangeListener {

    private var mRvShow: RecyclerView? = null

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
        findViewById<RadioGroup>(R.id.btn_recyclerviewScrollToPosition_configGroup).setOnCheckedChangeListener(this)

        mRvShow = findViewById(R.id.rv_recyclerviewScrollTpPosition_show)
        mRvShow?.layoutManager = LinearLayoutManager(this)
        mRvShow?.adapter = ItemAnimatorAdapter(mContentList)
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.btn_recyclerviewScrollToPosition_horizontal -> {
                mRvShow?.apply {
                    val layoutParams = layoutParams
                    layoutParams.height = 600
                    setLayoutParams(layoutParams)

                    val layoutManager = LinearLayoutManager(mActivity, RecyclerView.HORIZONTAL, false)
                    mRvShow?.layoutManager = layoutManager
//                    mRvShow?.adapter?.notifyDataSetChanged()
                }
            }
            R.id.btn_recyclerviewScrollToPosition_verticalFixHeight -> {
                mRvShow?.apply {
                    val layoutParams = layoutParams
                    layoutParams.height = 1000
                    setLayoutParams(layoutParams)

                    val layoutManager = LinearLayoutManager(mActivity)
                    mRvShow?.layoutManager = layoutManager
//                    mRvShow?.adapter?.notifyDataSetChanged()
                }
            }
            R.id.btn_recyclerviewScrollToPosition_verticalMatchParent -> {
                mRvShow?.apply {
                    val layoutParams = layoutParams
                    layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT
                    setLayoutParams(layoutParams)

                    val layoutManager = LinearLayoutManager(mActivity)
                    mRvShow?.layoutManager = layoutManager
//                    mRvShow?.adapter?.notifyDataSetChanged()
                }
            }
        }
    }
}