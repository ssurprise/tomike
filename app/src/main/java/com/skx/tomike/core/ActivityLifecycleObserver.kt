package com.skx.tomike.core

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.skx.tomike.core.AppActivityStackManager.pop
import com.skx.tomike.core.AppActivityStackManager.put

/**
 * 描述 : Activity 生命周期观察者
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/10/9 1:53 下午
 */
class ActivityLifecycleObserver : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        put(activity)
    }

    override fun onActivityStarted(activity: Activity) {}
    override fun onActivityResumed(activity: Activity) {}
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {
        pop()
    }
}