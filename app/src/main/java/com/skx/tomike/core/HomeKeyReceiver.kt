package com.skx.tomike.core

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter


/**
 * 描述 : Home键广播接受者
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/3/30 11:24 下午
 */
class HomeKeyReceiver : BroadcastReceiver() {

    private var onHomeKeyClickListener: (() -> Unit)? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.run {
            when (this.getStringExtra("reason") ?: "") {
                "homekey" -> {// 按HOME键
                    onHomeKeyClickListener?.invoke()
                }
                "recentapps" -> {// 长按HOME键

                }
                else -> {
                }
            }
        }
    }

    fun setOnHomeKeyClickListener(listener: (() -> Unit)) {
        this.onHomeKeyClickListener = listener
    }

    fun register(context: Context) {
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
        context.registerReceiver(this, filter)
    }
}