package com.skx.tomike.tanklaboratory.widget.activity

import com.skx.tomike.tanklaboratory.R
import com.skx.tomikecommonlibrary.base.BaseViewModel
import com.skx.tomikecommonlibrary.base.SkxBaseActivity
import com.skx.tomikecommonlibrary.base.TitleConfig


/**
 * 描述 : 自定义ShapeView
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020-03-18 23:10
 */
class ShapeViewActivity : SkxBaseActivity<BaseViewModel>() {

    override fun initParams() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_shape_view
    }

    override fun subscribeEvent() {
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("自定义ShapeView").create()
    }
}
