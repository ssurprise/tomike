package com.skx.common.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.skx.common.R

abstract class SkxBaseActivity<T : BaseViewModel?> : BaseMvvmActivity<T>() {

    @JvmField
    protected val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        nativeThemeStyle()
        super.onCreate(savedInstanceState)
        logPrinter("onCreate")
        initParams()
        initContentView()
        initView()
        subscribeEvent()
    }

    /**
     * 初始化主题样式
     */
    protected open fun nativeThemeStyle() {}

    private fun initContentView() {
        val titleConfig = configHeaderTitle()
        if (titleConfig == null) {
            // 没有配置title 信息，加载子类布局
            setContentView(layoutId())
            return
        }
        setContentView(R.layout.layout_base_ui)

        // 配置title view
        if (titleConfig.customTitleView == null) {
            buildDefaultTitleView(titleConfig)
        } else {
            buildCustomTitleView(titleConfig)
        }

        // 配置内容view
        val view = findViewById<FrameLayout>(R.id.fl_baseUI_content)
        val inflate = LayoutInflater.from(this).inflate(layoutId(), view, false)
        if (inflate != null) {
            view.addView(inflate)
        }
    }

    private fun buildDefaultTitleView(config: TitleConfig) {
        // 标题
        val mTvTitle = findViewById<TextView>(R.id.tv_baseUI_header_title)
        mTvTitle.text = config.titleText
        if (config.titleTextColor > 0) {
            mTvTitle.setTextColor(config.titleTextColor)
        }
        if (config.titleTextSize > 0) {
            mTvTitle.textSize = config.titleTextSize.toFloat()
        }

        // 左边返回按钮
        val mBtnBack = findViewById<ImageView>(R.id.iv_baseUI_header_back)
        if (config.backBtnRes > 0) {
            mBtnBack.setImageResource(config.backBtnRes)
        }
        mBtnBack.setOnClickListener { v: View? ->
            if (config.backBtnClickListener == null) {
                finish()
                return@setOnClickListener
            }
            config.backBtnClickListener.onClick(v)
        }

        // 右边更多按钮
        val mBtnMore = findViewById<ImageView>(R.id.iv_baseUI_header_more)
        if (config.moreBtnRes > 0) {
            mBtnMore.setImageResource(config.moreBtnRes)
        }
        mBtnMore.setOnClickListener { v: View? ->
            if (config.moreBtnClickListener == null) {
                finish()
                return@setOnClickListener
            }
            config.moreBtnClickListener.onClick(v)
        }
    }

    private fun buildCustomTitleView(config: TitleConfig?) {
        if (config == null || config.customTitleView == null) {
            return
        }
        val mTitleWrap = findViewById<RelativeLayout>(R.id.rl_baseUI_header_title)
        mTitleWrap.removeAllViews()
        mTitleWrap.addView(config.customTitleView)
    }

    /**
     * 初始化参数
     */
    protected abstract fun initParams()

    /**
     * 配置页面标题
     */
    protected open fun configHeaderTitle(): TitleConfig? {
        return null
    }

    /**
     * 获取页面布局
     *
     * @return 页面布局
     */
    @LayoutRes
    protected abstract fun layoutId(): Int

    /**
     * 初始化view
     */
    protected abstract fun initView()

    /**
     * 事件订阅。可用于监听LiveData 的变化
     */
    protected open fun subscribeEvent() {}
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        logPrinter("onNewIntent")
    }

    override fun onStart() {
        super.onStart()
        logPrinter("onStart")
    }

    override fun onResume() {
        super.onResume()
        logPrinter("onResume")
    }

    override fun onPause() {
        super.onPause()
        logPrinter("onPause")
    }

    override fun onStop() {
        super.onStop()
        logPrinter("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logPrinter("onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        logPrinter("onRestart")
    }

    private fun logPrinter(content: String) {
        Log.i("ActivityLifecycle", "$TAG -> $content")
    }
}