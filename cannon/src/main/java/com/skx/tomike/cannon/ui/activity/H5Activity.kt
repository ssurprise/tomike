package com.skx.tomike.cannon.ui.activity

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_H5
import com.skx.tomike.cannon.ui.activity.web.JavascriptBridge
import com.skx.tomike.cannon.ui.activity.web.MyWebView


/**
 * 描述 : H5 内置浏览器
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/5/26 12:30 上午
 */
@Route(path = ROUTE_PATH_H5)
class H5Activity : SkxBaseActivity<BaseViewModel<*>>() {

    private var mJsBridge: JavascriptBridge? = null

    private val mWebView: MyWebView by lazy {
        findViewById(R.id.wv_h5_browser)
    }

    override fun initParams() {
    }

    override fun layoutId(): Int {
        return R.layout.activity_internal_h5
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        findViewById<TextView>(R.id.id_internalH5_n2js_load).setOnClickListener {
            mJsBridge?.callJs("WebView.loadUrl()")
        }
        findViewById<TextView>(R.id.id_internalH5_n2js_load_detail).text = "优点：方便简洁\n缺点：效率低；获取返回值麻烦"
        findViewById<TextView>(R.id.id_internalH5_n2js_evaluate_detail).text = "优点：效率高,取返回值方便\n缺点：向下兼容性差（仅Android 4.4 以上可用）"
        findViewById<TextView>(R.id.id_internalH5_n2js_evaluate).setOnClickListener {
            mJsBridge?.callJsByE("WebView.evaluateJavascript()")
        }
        mWebView.setWebChromeClient(CustomWebViewChromeClient())
        mWebView.loadUrl("file:///android_asset/android_native_2_js.html")

        mJsBridge = JavascriptBridge(mWebView)
        mJsBridge?.registerJavascriptInterface()
        mJsBridge?.setPayListener(object : JavascriptBridge.PayProcessListener{
            override fun payStart() {
                TODO("Not yet implemented")
            }

            override fun payFinish(result: String) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mJsBridge?.unregisterJavascriptInterface()
    }

    class CustomWebViewChromeClient : WebChromeClient() {

        /**
         * 拦截js的警告框，没有返回值
         *
         * @param view
         * @param url     网页地址
         * @param message 代表 alert（）里的内容（不是url）
         * @param result
         * @return
         */
//        @Override
//        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
//            //如不想弹出网页上的对话框，可直接拦截，并自定义对话框
////            AlertDialog.Builder builder = new AlertDialog.Builder(WebViewActivity.this);
////            builder.setTitle("Alert")
////                    .setMessage(message)
////                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
////                        @Override
////                        public void onClick(DialogInterface dialog, int which) {
////                            result.confirm();
////                        }
////                    })
////                    .setCancelable(false)
////                    .create()
////                    .show();
////            return true;
//            return super.onJsAlert(view, url, message, result);
//        }
    }

    /*
            调用方式            优点          缺点                  使用场景
            loadUrl()	     方便简洁	   效率低；	        不需要获取返回值，
                                        获取返回值麻烦         对性能要求较低时

        evaluateJavascript()  效率高	     向下兼容性差         Android 4.4 以上
                                    （仅Android4.4以上可用）  需要获取返回值

     */

}