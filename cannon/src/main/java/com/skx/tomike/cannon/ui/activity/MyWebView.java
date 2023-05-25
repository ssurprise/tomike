package com.skx.tomike.cannon.ui.activity;


import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import static android.content.Context.MODE_PRIVATE;

class MyWebView extends android.webkit.WebView {

    public MyWebView(@NonNull Context context) {
        super(context);
        initWebView();
    }

    public MyWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initWebView();
    }

    public MyWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWebView();
    }

    public MyWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initWebView();
    }

    private void initWebView() {
        // 清除网页访问留下的缓存，
        // 由于内核缓存是全局的因此这个方法不仅仅针对webview而是针对整个应用程序
        this.clearCache(true);
        // 清除当前webview访问的历史记录，
        // 只会webview访问历史记录里的所有记录除了当前访问记录.
        this.clearHistory();
        // 这个api仅仅清除自动完成填充的表单数据，并不会清除WebView存储到本地的数据。
        this.clearFormData();
        // 让ToolBar处于系统状态栏下方
        this.setFitsSystemWindows(true);
        // 在网页工具中调试webview
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            android.webkit.WebView.setWebContentsDebuggingEnabled(BuildConfig.isDebug());
//        }

        /**
         * setBlockNetworkImage 是否显示网络图像
         * setCacheMode 设置缓冲的模式
         * setDefaultFontSize 设置默认的字体大小
         * setDefaultTextEncodingName 设置在解码时使用的默认编码
         * setFixedFontFamily 设置固定使用的字体
         * setLightTouchEnabled 设置用鼠标激活被选项
         * setSupportZoom 设置是否支持变焦
         * */
        WebSettings ws = this.getSettings();
        // 数据缓存_手动开启DOM Storage
        ws.setDomStorageEnabled(true);
        // 数据缓存_手动开启（AppCache）
        ws.setAppCacheEnabled(true);
        // 设置 AppCache 路径（setAppCachePath）和容量（setAppCacheMaxSize）
        ws.setAppCachePath(getContext().getCacheDir().getAbsolutePath());
        // 启用或禁止WebView访问文件数据
        ws.setAllowFileAccess(true);
        // 关闭内置的缩放功能
        ws.setBuiltInZoomControls(false);
        // 默认对缩放比例有限制，导致用户体验不好，所以需要设置为使用任意比例缩放。
        ws.setUseWideViewPort(true);
        // 设置webView自适应手机屏幕
        ws.setLoadWithOverviewMode(true);
        // 设置当前webview是否需要加载图片
        ws.setLoadsImagesAutomatically(true);
        // 设置是否支持Javascript
        ws.setJavaScriptEnabled(true);
        // 支持通过js打开新的窗口
        ws.setJavaScriptCanOpenWindowsAutomatically(true);
        // 启用地理定位
        ws.setGeolocationEnabled(true);
        //开启 DOM 功能
        ws.setDomStorageEnabled(true);
        // 启用数据库
        ws.setDatabaseEnabled(true);
        //设置定位的数据库路径
        ws.setGeolocationDatabasePath(getContext().getDir("database", MODE_PRIVATE).getPath());
        // 设置布局方式
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        if (Build.VERSION.SDK_INT >= 21) {
            ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    /**
     * 释放资源
     */
    public void onDestroy() {
        clearCache(true);
        clearHistory();
        clearFormData();
        destroy();
    }
}
