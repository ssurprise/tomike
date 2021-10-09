package com.skx.tomike.tank.widget.activity

import android.util.Log
import android.widget.CheckBox
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R

/**
 * 描述 : CheckBox demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020-03-23 23:23
 */
class CheckBoxActivity : SkxBaseActivity<BaseViewModel?>() {

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("CheckBox 实验室").create()
    }

    override fun getLayoutId(): Int {
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