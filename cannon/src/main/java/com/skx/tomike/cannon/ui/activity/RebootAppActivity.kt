package com.skx.tomike.cannon.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R

/**
 * 描述 : 重启App
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/7/12 7:24 下午
 */
class RebootAppActivity : SkxBaseActivity<BaseViewModel>() {

    override fun initParams() {
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("重启App").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_reboot
    }

    override fun initView() {

    }

    fun rebootApp(view: View) {
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        intent?.apply {
            this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(this)
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy")
    }
}