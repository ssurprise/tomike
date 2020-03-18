package com.skx.tomike.tanklaboratory.widget.activity

import android.widget.TextView
import com.skx.tomike.tanklaboratory.R
import com.skx.tomikecommonlibrary.base.BaseViewModel
import com.skx.tomikecommonlibrary.base.SkxBaseActivity


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

    override fun useDefaultLayout(): Boolean {
        return true
    }

    override fun configHeaderTitleView(title: TextView) {
        title.text = "自定义ShapeView"
    }
}
