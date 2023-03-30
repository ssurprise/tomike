package com.skx.tomike.core

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


/**
 * 描述 :
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/3/30 11:24 下午
 */
class HomeKeyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.run {
            when (this.getStringExtra("reason") ?: "") {
                "homekey" -> {// 按HOME键

                }
                "recentapps" -> {// 长按HOME键

                }
            }
        }
    }
}