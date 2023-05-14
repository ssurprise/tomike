package com.skx.tomike.tank.widget.activity

import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.*
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_VIEW_FOCUS

/**
 * 描述 : view 焦点
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/1 7:46 PM
 */
@Route(path = ROUTE_PATH_VIEW_FOCUS)
class ViewFocusActivity : SkxBaseActivity<BaseViewModel<*>>() {

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("EditText 焦点").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_view_focus
    }

    override fun initView() {
        findViewById<EditText>(R.id.et_viewFocus_name).apply {
            tag = "姓名输入框EditText"
            onFocusChangeListener = mFocusChangeListener
        }
        findViewById<EditText>(R.id.et_viewFocus_nickname).apply {
            tag = "昵称输入框EditText"
            onFocusChangeListener = mFocusChangeListener
        }
        findViewById<RadioGroup>(R.id.rg_viewFocus_sex).apply {
            tag = "性别单选框Group"
            onFocusChangeListener = mFocusChangeListener
            setOnCheckedChangeListener { group, checkedId ->
                val child = group.findViewById<View>(checkedId)
                Log.e(TAG, (child.tag?.toString() ?: "") + " -> 触发了点击事件")
            }
        }
        findViewById<RadioButton>(R.id.rbtn_viewFocus_sexGroup_man).apply {
            tag = "性别单选框男RadioButton"
            onFocusChangeListener = mFocusChangeListener
        }
        findViewById<RadioButton>(R.id.rbtn_viewFocus_sexGroup_women).apply {
            tag = "性别单选框女RadioButton"
            onFocusChangeListener = mFocusChangeListener
        }
        findViewById<TextView>(R.id.tv_viewFocus_text).apply {
            tag = "纯文本TextView"
            onFocusChangeListener = mFocusChangeListener
            isFocusable = true
            isFocusableInTouchMode = true
            requestFocus()
            setOnClickListener {
                Log.e(TAG, (it.tag?.toString() ?: "") + " -> 触发了点击事件")
            }
        }

        findViewById<Button>(R.id.btn_viewFocus_submit).apply {
            tag = "提交按钮button"
            onFocusChangeListener = mFocusChangeListener
            setOnClickListener {
                Log.e(TAG, (it.tag?.toString() ?: "") + " -> 触发了点击事件")
            }
        }
    }

    private val mFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
        Log.e(TAG, (v.tag?.toString() ?: "") + " -> " + if (hasFocus) "获得焦点" else "失去焦点")
    }
}