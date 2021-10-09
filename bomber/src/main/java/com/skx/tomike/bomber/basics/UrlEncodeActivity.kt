package com.skx.tomike.bomber.basics

import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.bomber.R
import java.net.URLDecoder
import java.net.URLEncoder

class UrlEncodeActivity : SkxBaseActivity<BaseViewModel>() {

    private val mEvOriginalVal: EditText by lazy {
        findViewById(R.id.et_urlEncode_original_value)
    }
    private val mTvResult: TextView by lazy {
        findViewById(R.id.tv_urlEncode_result)
    }

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("URL编码").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_url_encode
    }

    override fun initView() {
        findViewById<View>(R.id.tv_urlEncode_encoder).setOnClickListener {
            val oriVal = mEvOriginalVal.text.toString()
            val encodeResult: String? = try {
                URLEncoder.encode(oriVal, URL_ENCODE)
            } catch (e: Exception) {
                e.printStackTrace()
                e.message
            }
            mTvResult.text = encodeResult
        }
        findViewById<View>(R.id.tv_urlEncode_decoder).setOnClickListener {
            val oriVal = mEvOriginalVal.text.toString()
            val decodeResult: String? = try {
                URLDecoder.decode(oriVal, URL_ENCODE)
            } catch (e: Exception) {
                e.printStackTrace()
                e.message
            }
            mTvResult.text = decodeResult
        }
    }

    companion object {
        private const val URL_ENCODE = "UTF-8"
    }
}