package com.skx.tomike.tank.widget.activity

import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.widget.viewmodel.SwipeRefreshViewModel
import java.util.*

/**
 * 描述 : SwipeRefreshLayout demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020-03-18 22:11
 */
class SwipeRefreshLayoutActivity : SkxBaseActivity<SwipeRefreshViewModel>(), OnRefreshListener {

    private var mSwipeLayout: SwipeRefreshLayout? = null
    private var mAdapter: ArrayAdapter<String>? = null
    private val mData: MutableList<String> = ArrayList()

    override fun initParams() {
        mData.addAll(listOf("Java", "Javascript", "C++", "Ruby", "Json", "HTML"))
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_swipe_refresh_layout
    }

    override fun subscribeEvent() {
        mViewModel?.testDataLiveData?.observe(this, { strings: List<String>? ->
            strings?.run {
                mData.addAll(this)
                mAdapter?.notifyDataSetChanged()
            }
            mSwipeLayout?.isRefreshing = false
        })
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("SwipeRefreshLayout 下拉刷新").create()
    }

    override fun initView() {
        mSwipeLayout = findViewById(R.id.srl_swipeRefreshDemo_warp)
        mSwipeLayout?.setOnRefreshListener(this)
        val lvTestData = findViewById<ListView>(R.id.lv_swipeRefreshDem_content)
        lvTestData.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mData)
                .also { mAdapter = it }
    }

    override fun onRefresh() {
        mViewModel!!.loadData()
    }
}