package com.skx.tomike.bomber.basics

import android.util.Base64
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.bomber.R
import com.skx.tomike.bomber.ROUTE_PATH_BASE64

@Route(path = ROUTE_PATH_BASE64)
class Base64Activity : SkxBaseActivity<BaseViewModel>() {

    private val mEvOriginalVal: EditText by lazy {
        findViewById(R.id.et_base64_original_value)
    }
    private val mTvResult: TextView by lazy {
        findViewById(R.id.tv_base64_result)
    }

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("Base64 加密/解密").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_base64_encode
    }

    override fun initView() {
        findViewById<View>(R.id.tv_base64_encoder).setOnClickListener {
            val oriVal = mEvOriginalVal.text.toString()
            val encodeResult: String? = try {
                Base64.encodeToString(oriVal.toByteArray(), Base64.DEFAULT)
            } catch (e: Exception) {
                e.printStackTrace()
                e.message
            }
            mTvResult.text = encodeResult
        }
        findViewById<View>(R.id.tv_base64_decoder).setOnClickListener {
            val oriVal = mEvOriginalVal.text.toString()
            val decodeResult: String? = try {
                String(Base64.decode(oriVal, Base64.DEFAULT))
            } catch (e: Exception) {
                e.printStackTrace()
                e.message
            }
            mTvResult.text = decodeResult
        }
    }
}