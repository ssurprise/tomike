package com.skx.tomike.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.tomike.R

/**
 * 启动图、欢迎页
 */
class WelcomeActivity : SkxBaseActivity<BaseViewModel?>(), View.OnClickListener {

    private val handler = Handler(Looper.myLooper()!!)
    private val runnable = Runnable { gotoHomepage() }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= 28) {
            val lp = window.attributes
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.attributes = lp
        }
        super.onCreate(savedInstanceState)
    }

    override fun initParams() {}

    override fun layoutId(): Int {
        return R.layout.activity_welcome
    }

    override fun initView() {
        findViewById<View>(R.id.iv_welcome_mainImg).setOnClickListener(this)
        findViewById<View>(R.id.tv_welcome_skipBtn).setOnClickListener(this)
        handler.postDelayed(runnable, 1500)
    }

    private fun gotoHomepage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_welcome_skipBtn, R.id.iv_welcome_mainImg -> {
                handler.removeCallbacks(runnable)
                gotoHomepage()
            }
        }
    }
}