package com.skx.tomike.bomber.basics

import android.widget.EditText
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.common.utils.ToastTool
import com.skx.tomike.bomber.R
import com.skx.tomike.bomber.ROUTE_PATH_URL_PARSE
import java.net.MalformedURLException
import java.net.URL

/**
 * 描述 : Url 解析
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/7/27 12:26 AM
 */
@Route(path = ROUTE_PATH_URL_PARSE)
class UrlParseActivity : SkxBaseActivity<BaseViewModel<*>>() {

    private var mTargetUrl = "https://haokan.baidu.com/v?vid=17099850856972684618"
    private val mTvResult: TextView by lazy {
        findViewById(R.id.tv_urlParse_result)
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("url 解析").create()
    }

    override fun initParams() {}

    override fun layoutId(): Int {
        return R.layout.activity_url_parse
    }

    override fun initView() {
        val mEtTargetUrl = findViewById<EditText>(R.id.tv_urlParse_targetUrl)
        mEtTargetUrl.setText(mTargetUrl)
        urlParse(mTargetUrl)

        findViewById<TextView>(R.id.tv_urlParse_startParsing).setOnClickListener {
            mTargetUrl = mEtTargetUrl.text.toString()
            urlParse(mTargetUrl)
        }
    }

    private fun urlParse(urlStr: String) {
        try {
            val url = URL(urlStr)
            val sb = StringBuilder()

            // The protocol to use (ftp, http, nntp, ... etc.)
            sb.append("协议为（protocol）：\n").append(url.protocol).append("\n\n")
                    // The authority part of this URL.
                    .append("验证信息（authority）：\n").append(url.authority).append("\n\n")
                    // The host name to connect to.
                    .append("主机名（host）：\n").append(url.host).append("\n\n")
                    // The protocol port to connect to.
                    .append("端口（port）：\n").append(url.port).append("\n\n")
                    .append("默认端口（DefaultPort）：\n").append(url.defaultPort).append("\n\n")
                    // The specified file name on that host. {@code file} is  defined as {@code path[?query]}
                    .append("文件名及请求参数（file）：\n").append(url.file).append("\n\n")
                    // The path part of this URL.
                    .append("路径（path）：\n").append(url.path).append("\n\n")
                    // The query part of this URL.
                    .append("请求参数（query）：\n").append(url.query).append("\n\n")
                    .append("定位位置（ref）：\n").append(url.ref).append("\n\n")
            mTvResult.text = sb.toString()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
            ToastTool.showToast(mActivity, e.message)
            mTvResult.text = ""
        }
    }
}