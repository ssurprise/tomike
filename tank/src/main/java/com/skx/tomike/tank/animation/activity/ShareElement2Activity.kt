package com.skx.tomike.tank.animation.activity

import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R

/**
 * 共享元素2
 */
class ShareElement2Activity : SkxBaseActivity<BaseViewModel>() {

    override fun initParams() {}

    override fun getLayoutId(): Int {
        return R.layout.activity_share_element2
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("共享元素 - 界面2").create()
    }

    override fun initView() {}
}