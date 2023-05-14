package com.skx.tomike.tank.widget.activity

import android.util.Log
import android.widget.CheckBox
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_CHECKBOX

/**
 * 描述 : CheckBox demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020-03-23 23:23
 */
@Route(path = ROUTE_PATH_CHECKBOX)
class CheckBoxActivity : SkxBaseActivity<BaseViewModel<*>>() {

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("CheckBox 实验室").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_check_box
    }

    override fun initView() {
        val checkBox = findViewById<CheckBox>(R.id.cb_checkBoxTest_1)
//        val checkBox2 = findViewById<CheckBox>(R.id.cb_checkBoxTest_2)
//        val checkBox3 = findViewById<CheckBox>(R.id.cb_checkBoxTest_3)
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            Log.e(TAG, "buttonView：$isChecked")
        }
    }
}