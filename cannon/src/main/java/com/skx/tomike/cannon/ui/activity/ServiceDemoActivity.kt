package com.skx.tomike.cannon.ui.activity

import android.app.Service
import android.content.*
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_SERVICE
import java.util.concurrent.ScheduledExecutorService

/**
 * 描述 : 服务demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/8/30 5:00 下午
 */
@Route(path = ROUTE_PATH_SERVICE)
class ServiceDemoActivity : SkxBaseActivity<BaseViewModel<*>>(), View.OnClickListener {

    private val mTvLogcat by lazy {
        findViewById<TextView>(R.id.tv_service_logcat)
    }

    private val receiver: ServiceBroadcastReceiver = ServiceBroadcastReceiver()
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.e("MyService", "ServiceConnection - onServiceConnected")
            mTvLogcat.append("onServiceConnected. name=${name?.shortClassName}\n")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.e("MyService", "ServiceConnection - onServiceDisconnected")
            mTvLogcat.append("onServiceDisconnected. name=${name}\n")
        }
    }

    override fun initParams() {
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("服务Service").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_service_test
    }

    override fun initView() {
        findViewById<TextView>(R.id.btn_service_startService).setOnClickListener(this)
        findViewById<TextView>(R.id.btn_service_stopService).setOnClickListener(this)
        findViewById<TextView>(R.id.btn_service_bindService).setOnClickListener(this)
        findViewById<TextView>(R.id.btn_service_unbindService).setOnClickListener(this)
        findViewById<TextView>(R.id.btn_service_startAndBindService).setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(receiver, IntentFilter("com.skx.tomike.cannon.test"))
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
                val intent = Intent(this, MyService::class.java)
                bindService(intent, connection, BIND_AUTO_CREATE)
            }
            R.id.btn_service_unbindService -> {
                unbindService(connection)
            }
            R.id.btn_service_startAndBindService -> {
                val intent = Intent(this, MyService::class.java)
                startService(intent)
                val bindService = bindService(intent, connection, BIND_AUTO_CREATE)
                Log.e("MyService", "bindService，result=$bindService")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    inner class ServiceBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val content = intent?.getStringExtra("msg") ?: ""
            mTvLogcat?.append("$content\n")
        }
    }
}

class MyService : Service() {

    private var newScheduledThreadPool: ScheduledExecutorService? = null

    private var time: Int = 0

    private val mIntent = Intent("com.skx.tomike.cannon.test")

    // onCreate()方法只会创建调用一次，无论是何种启动方式
    override fun onCreate() {
        super.onCreate()
        sendMsgNotify("MyService-onCreate")
    }

    // startService启动方式：重复调用时，startCommand()会每次都会被调用
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        sendMsgNotify("MyService-onStartCommand")

        val stringExtra = intent?.getStringExtra("cmd")
        if("download" ==stringExtra){
            download()
        }
        return START_STICKY
    }

    private fun download() {

    }

    //  bindService启动方式：重复调用时，onCreate()与onBind()都只会调用一次
    override fun onBind(intent: Intent?): IBinder? {
        sendMsgNotify("MyService-onBind")
        return MyBinder()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        sendMsgNotify("MyService-onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        sendMsgNotify("MyService-onDestroy")
        super.onDestroy()
        newScheduledThreadPool?.shutdown()
    }

    private fun sendMsgNotify(msg: String) {
        Log.e("MyService", msg)
        mIntent.putExtra("msg", msg)
        sendBroadcast(mIntent)
    }
}

class MyBinder : Binder()