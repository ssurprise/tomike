package com.skx.tomike.tank.widget.activity

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_DYNAMIC_MSG_LAYOUT
import com.skx.tomike.tank.widget.view.DynamicMsgLayout


/**
 * 描述 : 动态消息展示
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2022/1/4 4:44 下午
 */
@Route(path = ROUTE_PATH_DYNAMIC_MSG_LAYOUT)
class DynamicMsgLayoutActivity : SkxBaseActivity<BaseViewModel>() {

    private val root: DynamicMsgLayout by lazy {
        findViewById(R.id.dslMessages)
    }
    private val mRgPosOption: RadioGroup by lazy {
        findViewById(R.id.rg_dynamicMsg_rg)
    }
    private val mEtMsgText: EditText by lazy {
        findViewById(R.id.et_dynamicMsg_msgTxt)
    }
    private val mBtnSend: Button by lazy {
        findViewById(R.id.btn_dynamicMsg_send)
    }

    private var msgPos: Int = 0

    private val handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                0 -> {
                }
                1 -> {

                }
            }
        }
    }

    override fun initParams() {
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("自定义Layout-动态消息DynamicMsgLayout").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_dynamic_msg_layout
    }

    override fun initView() {
        mRgPosOption.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_dynamicMsg_pos_0 -> msgPos = 0
                R.id.rb_dynamicMsg_pos_1 -> msgPos = 1
                R.id.rb_dynamicMsg_pos_2 -> msgPos = 2
                R.id.rb_dynamicMsg_pos_3 -> msgPos = 3
                R.id.rb_dynamicMsg_pos_4 -> msgPos = 4
                R.id.rb_dynamicMsg_pos_5 -> msgPos = 5
            }
        }
        mBtnSend.setOnClickListener {
            root.sendMessage(msgPos, mEtMsgText.text.toString())
        }
    }
}