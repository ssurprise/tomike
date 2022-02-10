package com.skx.tomike.tank.widget.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_SHAPE_VIEW


/**
 * 描述 : 自定义ShapeView
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020-03-18 23:10
 */
@Route(path = ROUTE_PATH_SHAPE_VIEW)
class ShapeViewActivity : SkxBaseActivity<BaseViewModel>() {

    override fun initParams() {
    }

    override fun layoutId(): Int {
        return R.layout.activity_shape_view
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("自定义ShapeView").create()
    }

    override fun initView() {
    }
}
