package com.skx.tomike.cannon.ui.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_BROADCAST

/**
 * 描述 : 广播demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/8/30 5:00 下午
 */
@Route(path = ROUTE_PATH_BROADCAST)
class BroadcastDemoActivity : SkxBaseActivity<BaseViewModel>() {

    private val mBroadcastReceiver = SuperBroadcastReceiver()

    private val mTvLogcat by lazy {
        findViewById<TextView>(R.id.tv_broadcast_content).apply {
            this.movementMethod = ScrollingMovementMethod.getInstance()
        }
    }

    override fun initParams() {
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("广播Broadcast").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_broadcast_test
    }

    override fun initView() {

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_BATTERY_CHANGED)// 电量变化
        filter.addAction(Intent.ACTION_BATTERY_LOW)
        filter.addAction(Intent.ACTION_BATTERY_OKAY)
        filter.addAction(Intent.ACTION_POWER_CONNECTED)// 插电
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)// 断电
        filter.addAction(Intent.ACTION_USER_PRESENT)// 屏幕解锁
        filter.addAction(Intent.ACTION_USER_UNLOCKED)// 屏幕解锁
        filter.addAction(Intent.ACTION_SCREEN_ON)// 亮屏
        filter.addAction(Intent.ACTION_SCREEN_OFF)// 息屏
        registerReceiver(mBroadcastReceiver, filter)
    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mBroadcastReceiver)
    }

    inner class SuperBroadcastReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.action?.run {
                mTvLogcat.append(this + "\n")
                when (this) {
                    Intent.ACTION_POWER_CONNECTED -> {
                        // 插电
                    }
                    Intent.ACTION_POWER_DISCONNECTED -> {
                        // 断电
                    }
                    Intent.ACTION_BATTERY_CHANGED -> {
                        // 电量变化
                    }
                }
            }
        }

    }
}