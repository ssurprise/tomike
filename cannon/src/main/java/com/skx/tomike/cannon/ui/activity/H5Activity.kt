package com.skx.tomike.cannon.ui.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_H5

/**
 * 描述 : H5 内置浏览器
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/5/26 12:30 上午
 */
@Route(path = ROUTE_PATH_H5)
class H5Activity : SkxBaseActivity<BaseViewModel<*>>() {

    private val wv_h5_browser:MyWebView by lazy {
        findViewById(R.id.wv_h5_browser)
    }

    override fun initParams() {
    }

    override fun layoutId(): Int {
        return R.layout.activity_internal_h5
    }

    override fun initView() {
    }
}