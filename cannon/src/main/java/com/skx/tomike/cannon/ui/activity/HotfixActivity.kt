package com.skx.tomike.cannon.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R

/**
 * 描述 : 热修复 - 微信Tinker
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/1/21 6:08 PM
 */
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