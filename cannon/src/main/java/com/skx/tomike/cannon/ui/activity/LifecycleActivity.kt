package com.skx.tomike.cannon.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R

/**
 * 描述 : Lifecycle demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/10 9:15 AM
 */
class LifecycleActivity : SkxBaseActivity<BaseViewModel?>() {

    private val tv: TextView by lazy {
        findViewById(R.id.tv_lifecycle_content)
    }

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("Lifecycle demo").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_lifecycle_test
    }

    override fun initView() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(Re(tv))
    }

    override fun onResume() {
        super.onResume()
        tv.append("在 onResume() 方法里添加另外一个 Observer \n")
        lifecycle.addObserver(Re2(tv))
    }
}

class Re(private val tv: TextView?) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.e("LifecycleActivity", "OnLifecycleEvent:create")
        tv?.append("OnLifecycleEvent-1:create \n")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.e("LifecycleActivity", "OnLifecycleEvent:start")
        tv?.append("OnLifecycleEvent-1:start \n")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.e("LifecycleActivity", "OnLifecycleEvent:resume")
        tv?.append("OnLifecycleEvent-1:resume \n")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.e("LifecycleActivity", "OnLifecycleEvent:pause")
        tv?.append("OnLifecycleEvent-1:pause \n")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.e("LifecycleActivity", "OnLifecycleEvent:stop")
        tv?.append("OnLifecycleEvent-1:stop \n")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.e("LifecycleActivity", "OnLifecycleEvent:destroy")
        tv?.append("OnLifecycleEvent-1:destroy \n")
    }
}

class Re2(private val tv: TextView?) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.e("LifecycleActivity", "OnLifecycleEvent-2:create")
        tv?.append("OnLifecycleEvent-2:create \n")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.e("LifecycleActivity", "OnLifecycleEvent-2:start")
        tv?.append("OnLifecycleEvent-2:start \n")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.e("LifecycleActivity", "OnLifecycleEvent-2:resume")
        tv?.append("OnLifecycleEvent-2:resume \n")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.e("LifecycleActivity", "OnLifecycleEvent-2:pause")
        tv?.append("OnLifecycleEvent-2:pause \n")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.e("LifecycleActivity", "OnLifecycleEvent-2:stop")
        tv?.append("OnLifecycleEvent-2:stop \n")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.e("LifecycleActivity", "OnLifecycleEvent-2:destroy")
        tv?.append("OnLifecycleEvent-2:destroy \n")
    }
}