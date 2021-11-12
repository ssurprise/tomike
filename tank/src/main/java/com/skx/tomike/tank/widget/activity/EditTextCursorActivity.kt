package com.skx.tomike.tank.widget.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTER_GROUP
import com.skx.tomike.tank.ROUTE_PATH_EDITTEXT_CURSOR

/**
 * 描述 : EditText光标修改
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/1 7:46 PM
 */
@Route(path = ROUTE_PATH_EDITTEXT_CURSOR, group = ROUTER_GROUP)
class EditTextCursorActivity : SkxBaseActivity<BaseViewModel?>() {

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("EditText 光标修改").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_edit_text_cursor
    }

    override fun initView() {}
}