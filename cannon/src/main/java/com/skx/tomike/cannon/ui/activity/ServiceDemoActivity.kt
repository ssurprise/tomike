package com.skx.tomike.cannon.ui.activity

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.TextView
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * 描述 : 服务demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/8/30 5:00 下午
 */
class ServiceDemoActivity : SkxBaseActivity<BaseViewModel>(), View.OnClickListener {

    override fun initParams() {
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("服务Service").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_service_test
    }

    override fun initView() {
        findViewById<TextView>(R.id.btn_service_startService).setOnClickListener(this)
        findViewById<TextView>(R.id.btn_service_stopService).setOnClickListener(this)
        findViewById<TextView>(R.id.btn_service_bindService).setOnClickListener(this)
        findViewById<TextView>(R.id.btn_service_unbindService).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_service_startService -> {
                val intent = Intent(this, MyService::class.java)
                startService(intent)
            }
            R.id.btn_service_stopService -> {
                val intent = Intent(this, MyService::class.java)
                stopService(intent)
            }
            R.id.btn_service_bindService -> {

            }
            R.id.btn_service_unbindService -> {

            }
        }
    }
}

class MyService : Service() {

    private var newScheduledThreadPool: ScheduledExecutorService? = null

    private var time: Int = 0

    // onCreate()方法只会创建调用一次，无论是何种启动方式
    override fun onCreate() {
        super.onCreate()
        Log.e("MyService", "onCreate")
//        newScheduledThreadPool = Executors.newScheduledThreadPool(1)
//        newScheduledThreadPool?.scheduleAtFixedRate({
//            Log.e("MyService", "子线程执行")
//            time++
//            if (time > 10) {
//                stopSelf()
//            }
//        }, 0, 1, TimeUnit.SECONDS)
    }

    // startService启动方式：重复调用时，startCommand()会每次都会被调用
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("MyService", "onStartCommand")
        return START_STICKY
    }

    //  bindService启动方式：重复调用时，onCreate()与onBind()都只会调用一次
    override fun onBind(intent: Intent?): IBinder? {
        Log.e("MyService", "onBind")
        return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e("MyService", "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        newScheduledThreadPool?.shutdown()
        Log.e("MyService", "onDestroy")
    }
}

class MyServiceConnection : ServiceConnection {
    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        Log.e("MyService", "ServiceConnection - onServiceConnected")
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        Log.e("MyService", "ServiceConnection - onServiceDisconnected")
    }
}