package com.skx.tomike.cannon.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTER_GROUP
import com.skx.tomike.cannon.ROUTE_PATH_COUNT_DOWN_TIMER
import com.skx.tomike.cannon.ROUTE_PATH_hotfix

/**
 * 描述 : 热修复 - 微信Tinker
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/1/21 6:08 PM
 */
@Route(path = ROUTE_PATH_hotfix, group = ROUTER_GROUP)
class HotfixActivity : SkxBaseActivity<BaseViewModel>(), View.OnClickListener {

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("热修复 - 微信Tinker").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_hotfix_tinker
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onClick(v: View?) {

    }
}