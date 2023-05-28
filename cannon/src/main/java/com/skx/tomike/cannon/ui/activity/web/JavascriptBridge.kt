package com.skx.tomike.cannon.ui.activity.web

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import com.skx.common.utils.ToastTool

/**
 * 描述 : Android Native 和 js 互调桥类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/5/27 9:42 下午
 */
class JavascriptBridge(private val mWebView: WebView) {

    private var mPayListener: PayProcessListener? = null

    private val mHandler: Handler = object : Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                TAG_WHAT_START_PAY -> {

                }
                TAG_WHAT_GET_ACCESS_TOKEN -> {
                    ToastTool.showToast(mWebView.context, "收到了js 调用native getAccessToken()方法的命令")
                }
                else -> {
                }
            }
        }
    }

    fun registerJavascriptInterface() {
        mWebView.addJavascriptInterface(this, "isUp2You")
    }

    fun unregisterJavascriptInterface() {
        mWebView.removeJavascriptInterface("isUp2You")
    }


    /******************* 下面的方法为Android native 调用Js 提供的接口  */


    fun callJs(param: String) {
        mWebView.loadUrl("javascript:callJs('$param')")
    }

    fun callJsByE(param: String) {
        mWebView.evaluateJavascript("javascript:callJs('$param')", null)
    }


    /******************* 下面的方法为Android native 对外向Js 提供的接口  */


    /**
     * Android native 提供给js的调用接口。
     * 发起支付
     *
     * @param params
     */
    @JavascriptInterface
    fun startPay(params: String) {
        Log.e(TAG, "收到了js 调用native startPay()方法的命令，params=$params")
        // 调起js 方法，通知支付结果。

//        mPayListener?.payStart()
        mHandler.postDelayed({
//            mPayListener?.payFinish("成功")
            mWebView.evaluateJavascript("javascript:onPayResult('成功')", null)
        }, 500)
    }

    /**
     * Android native 提供给js的调用接口。
     * 获取访问token
     *
     * @param params
     */
    @JavascriptInterface
    fun getAccessToken(params: String) {
        Log.e(TAG, "收到了js 调用native getAccessToken()方法的命令，params=$params")
        mHandler.sendEmptyMessageDelayed(TAG_WHAT_GET_ACCESS_TOKEN, 2000)
    }

    interface PayProcessListener {
        fun payStart()
        fun payFinish(result: String)
    }

    fun setPayListener(payProcessListener: PayProcessListener) {
        this.mPayListener = payProcessListener
    }

    companion object {
        private const val TAG = "JavascriptBridge"
        private const val TAG_WHAT_START_PAY = 0
        private const val TAG_WHAT_GET_ACCESS_TOKEN = 1
    }
}