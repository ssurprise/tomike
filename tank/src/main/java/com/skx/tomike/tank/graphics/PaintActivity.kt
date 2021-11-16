package com.skx.tomike.tank.graphics

import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_PAINT


@Route(path = ROUTE_PATH_PAINT)
class PaintActivity : SkxBaseActivity<BaseViewModel>() {

    override fun initParams() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_paint_test
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("paint").create()
    }

    override fun initView() {
    }
}