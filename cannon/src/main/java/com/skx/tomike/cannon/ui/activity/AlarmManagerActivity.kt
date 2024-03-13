package com.skx.tomike.cannon.ui.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.common.loudspeaker.LoudSpeaker
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_ALARM_MANAGER
import java.util.*


/**
 * 描述 : 定时任务-alarmManager
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/8/30 5:00 下午
 */
@Route(path = ROUTE_PATH_ALARM_MANAGER)
class AlarmManagerActivity : SkxBaseActivity<BaseViewModel<*>>(), View.OnClickListener, Observer {

    private var alarmMgr: AlarmManager? = null

    private val mTvLogcat by lazy {
        findViewById<TextView>(R.id.tv_alarmManager_logcat)
    }

    override fun initParams() {
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("AlarmManager").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_alarm_manager
    }

    override fun initView() {
        findViewById<TextView>(R.id.btn_alarmManager_oneTime).setOnClickListener(this)
        findViewById<TextView>(R.id.btn_alarmManager_periodic).setOnClickListener(this)
        findViewById<TextView>(R.id.btn_alarmManager_cancel).setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alarmMgr = this.getSystemService(ALARM_SERVICE) as AlarmManager
        LoudSpeaker.addObserver(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_alarmManager_oneTime -> {
                startOneTimeWork()
            }
            R.id.btn_alarmManager_periodic -> {
                startPeriodicWork()
            }
            R.id.btn_alarmManager_cancel -> {
                stopWork()
            }
        }
    }

    private fun startOneTimeWork() {
        logPrint("按下'单次闹钟'按钮")
        val intent = Intent(this, AlarmManagerReceiver::class.java)
//        intent.action = "com.skx.tomike.action.alarm_manager"
        intent.putExtra("param", "once")

        val pendingIntent: PendingIntent =
            PendingIntent.getBroadcast(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val time = System.currentTimeMillis() + 3000// 设置当前时间
        alarmMgr?.set(AlarmManager.RTC_WAKEUP, time, pendingIntent)// 设置单次闹钟提醒
    }

    private fun startPeriodicWork() {
        logPrint("按下'重复闹钟'按钮")
        val intent = Intent(this, AlarmManagerReceiver::class.java)
        intent.action = "com.skx.tomike.action.alarm_manager"
        intent.putExtra("param", "periodic")

        val pendingIntent: PendingIntent =
            PendingIntent.getBroadcast(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val time = System.currentTimeMillis()// 设置当前时间
        alarmMgr?.setRepeating(AlarmManager.RTC_WAKEUP, time, 1000 * 60 * 5, pendingIntent)
    }

    private fun stopWork() {
        logPrint("按下'取消'按钮")
        val intent = Intent()
        intent.action = "com.skx.tomike.action.alarm_manager"
        intent.putExtra("param", "periodic")

        val pendingIntent: PendingIntent =
            PendingIntent.getBroadcast(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmMgr?.cancel(pendingIntent)
    }

    override fun update(o: Observable?, arg: Any?) {
        logPrint(arg.toString())
    }

    private fun logPrint(s: String) {
        Log.d(TAG, s)
        mTvLogcat?.append("\n$s")
    }

    override fun onDestroy() {
        super.onDestroy()
        LoudSpeaker.deleteObserver(this)
    }
}

class AlarmManagerReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action ?: ""
        val param = intent?.getStringExtra("param") ?: ""
        LoudSpeaker.sendMsg("AlarmManagerReceiver-onReceive action=${action}, param=${param}")
    }
}