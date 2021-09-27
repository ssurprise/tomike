package com.skx.tomike.activity

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.tomike.R

/**
 * 启动图、欢迎页
 */
class WelcomeActivity : SkxBaseActivity<BaseViewModel?>(), View.OnClickListener {

    private val handler = Handler(Looper.myLooper()!!)
    private val runnable = Runnable { gotoHomepage() }

    override fun initParams() {}

    override fun getLayoutId(): Int {
        return R.layout.activity_welcome
    }

    override fun initView() {
        findViewById<View>(R.id.iv_welcome_mainImg).setOnClickListener(this)
        findViewById<View>(R.id.tv_welcome_skipBtn).setOnClickListener(this)
        handler.postDelayed(runnable, 3000)
    }

    private fun gotoHomepage() {
        val intent = Intent(this, HomeActivity::class.java)
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