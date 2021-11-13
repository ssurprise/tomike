package com.skx.tomike.tank.widget.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_RADIOGROUP

/**
 * 描述 : RadioGroup demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/20 12:31 PM
 */
@Route(path = ROUTE_PATH_RADIOGROUP)
class RadioGroupActivity : SkxBaseActivity<BaseViewModel>() {

    override fun initParams() {
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("单选-RadioGroup").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_radio_group
    }

    override fun initView() {
    }
}
