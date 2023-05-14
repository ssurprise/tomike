package com.skx.tomike.tank.widget.activity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_SCROLLVIEW_RV
import com.skx.tomike.tank.widget.adapter.ItemAnimatorAdapter

/**
 * 描述 : ScrollView 嵌套RecyclerView
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/1/8 5:08 PM
 */
@Route(path = ROUTE_PATH_SCROLLVIEW_RV)
class ScrollViewAndRecyclerViewActivity : SkxBaseActivity<BaseViewModel<*>>() {

    private var mRvShow: RecyclerView? = null

    private val mCon: MutableList<String> = mutableListOf()

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("ScrollView 嵌套 RecyclerView").create()
    }

    override fun initParams() {
        var i = 0
        val j = 25
        while (i < j) {
            mCon.add("第" + i + "个")
            i++
        }
    }

    override fun layoutId() = R.layout.activity_scrollview_and_recyclerview

    override fun initView() {
        mRvShow = findViewById(R.id.rv_scrollViewAndRecyclerview_show)
        mRvShow?.layoutManager = LinearLayoutManager(this)
        mRvShow?.adapter = ItemAnimatorAdapter(mCon)
    }
}