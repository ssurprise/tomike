package com.skx.tomike.cannon.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R

/**
 * 描述 : registerForActivityResult 新的用于替换startActivityForResult
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/7/30 11:18 上午
 */
class WithCallbackActivity : SkxBaseActivity<BaseViewModel>() {

    private val mTvAcceptData: TextView by lazy {
        findViewById(R.id.tv_withCallBack_acceptValue)
    }
    private val mEtSendData: EditText by lazy {
        findViewById(R.id.tv_withCallBack_sendData_value)
    }

    private val registerForActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { it ->
            if (it.resultCode == RESULT_OK) {
                it.data?.takeIf {
                    it.hasExtra(KEY_DATA)
                }?.run {
                    mTvAcceptData.text = it.data?.getStringExtra(KEY_DATA)
                }
            }
        }

    private var mAcceptValue: String = ""

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("registerForActivityResult").create()
    }

    override fun initParams() {
        intent?.takeIf {
            it.hasExtra(KEY_DATA)
        }?.run {
            mAcceptValue = getStringExtra(KEY_DATA)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_with_callback
    }

    override fun initView() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTvAcceptData.text = mAcceptValue
    }

    fun startActivityWithData(view: View) {
        val intent = Intent(this@WithCallbackActivity, WithCallbackActivity::class.java)
        intent.putExtra(KEY_DATA, mEtSendData.text.toString())
        when (view.id) {
            R.id.btn_withCallBack_startActivity -> {
                registerForActivityResult.launch(intent)
            }
            R.id.btn_withCallBack_backWithData -> {
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    companion object {
        const val KEY_DATA = "data"
    }
}