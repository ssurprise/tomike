package com.skx.tomike.tank.widget.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTER_GROUP
import com.skx.tomike.tank.ROUTE_PATH_EDITTEXT_CURSOR
import com.skx.tomike.tank.ROUTE_PATH_FLOW_LAYOUT
import com.skx.tomike.tank.widget.adapter.FlowAdapter
import com.skx.tomike.tank.widget.view.FlowLayout

/**
 * 描述 : 流式布局
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/24 8:05 PM
 */
@Route(path = ROUTE_PATH_FLOW_LAYOUT, group = ROUTER_GROUP)
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