package com.skx.tomike.cannon.ui.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_LIFECYCLE

/**
 * 描述 : Lifecycle demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/10 9:15 AM
 */
@Route(path = ROUTE_PATH_LIFECYCLE)
class LifecycleActivity : SkxBaseActivity<BaseViewModel?>(), View.OnClickListener {

    private val tv: TextView by lazy {
        findViewById(R.id.tv_lifecycle_content)
    }

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("Lifecycle demo").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_lifecycle_test
    }

    override fun initView() {
        findViewById<Button>(R.id.btn_lifecycle_showDialog).setOnClickListener(this)
        findViewById<Button>(R.id.btn_lifecycle_openTransparentActivity).setOnClickListener(this)
        findViewById<Button>(R.id.btn_lifecycle_openNewActivity).setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(Re(tv))
    }

    override fun onResume() {
        super.onResume()
//        tv.append("在 onResume() 方法里添加另外一个 Observer \n")
//        lifecycle.addObserver(Re2(tv))
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("Activity_Lifecycle", "onRestart()")
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_lifecycle_showDialog -> {
                onPopDialog()
            }
            R.id.btn_lifecycle_openNewActivity -> {
                onOpenNewActivity()
            }
            R.id.btn_lifecycle_openTransparentActivity -> {
                openTransparentActivity()
            }
        }
    }

    private fun onPopDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("My AlertDialog")
        builder.setMessage("弹出dialog ，观察activity 声明周期的变化")
        builder.setCancelable(false)
        builder.setPositiveButton("关闭") { dialog, _ ->
            dialog.dismiss()
            Log.e("Lifecycle", "-> 关闭dialog")
            tv.append("关闭Dialog \n")
        }
        Log.e("Lifecycle", "-> 弹出dialog")
        tv.append("弹出Dialog \n")
        builder.show()
    }

    private fun onOpenNewActivity() {
        val intent = Intent(this, LifecycleActivity2::class.java)
        startActivity(intent)
    }

    private fun openTransparentActivity() {
        val intent = Intent(this, TransparentThemeActivity::class.java)
        startActivity(intent)
    }
}

class Re(private val tv: TextView?) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.e("Activity_Lifecycle", "Activity-Event:create")
        tv?.append("Event-1:create \n")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.e("Activity_Lifecycle", "Activity-Event:start")
        tv?.append("Event-1:start \n")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.e("Activity_Lifecycle", "Activity-Event:resume")
        tv?.append("Event-1:resume \n")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.e("Activity_Lifecycle", "Activity-Event:pause")
        tv?.append("Event-1:pause \n")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.e("Activity_Lifecycle", "Activity-Event:stop")
        tv?.append("Event-1:stop \n")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.e("Activity_Lifecycle", "Activity-Event:destroy")
        tv?.append("Event-1:destroy \n")
    }
}

class Re2(private val tv: TextView?) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.e("Activity_Lifecycle", "Activity-Event-2:create")
        tv?.append("Event-2:create \n")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.e("Activity_Lifecycle", "Activity-Event-2:start")
        tv?.append("Event-2:start \n")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.e("Activity_Lifecycle", "Activity-Event-2:resume")
        tv?.append("Event-2:resume \n")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.e("Activity_Lifecycle", "Activity-Event-2:pause")
        tv?.append("Event-2:pause \n")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.e("Activity_Lifecycle", "Activity-Event-2:stop")
        tv?.append("Event-2:stop \n")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.e("Activity_Lifecycle", "Activity-Event-2:destroy")
        tv?.append("Event-2:destroy \n")
    }
}