package com.skx.tomike.tank.widget.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_EDITTEXT_CURSOR
import com.skx.tomike.tank.ROUTE_PATH_EDITTEXT_SINGLE_MULTI_LINES

/**
 * 描述 : EditText单行、多行显示
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/1 7:46 PM
 */
@Route(path = ROUTE_PATH_EDITTEXT_SINGLE_MULTI_LINES)
class EditTextSingleMultiActivity : SkxBaseActivity<BaseViewModel<*>>() {

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("EditText单行、多行显示").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_edit_text_single_multi_lines
    }

    override fun initView() {}
}