package com.skx.tomike.tank.widget.activity

import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.widget.adapter.FlowAdapter
import com.skx.tomike.tank.widget.view.FlowLayout

/**
 * 描述 : 流式布局
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/24 8:05 PM
 */
class FlowLayoutActivity : SkxBaseActivity<BaseViewModel?>() {

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("流式布局").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_flow_layout
    }

    override fun initView() {
        val flowLayout = findViewById<FlowLayout>(R.id.fl_flowLayoutTest_content)
        flowLayout.setAdapter(FlowAdapter())
    }
}