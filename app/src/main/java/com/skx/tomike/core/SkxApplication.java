package com.skx.tomike.core;

import android.app.Application;
import android.os.StrictMode;

import com.skx.tomike.BuildConfig;

public class SkxApplication extends Application {

    @Override
    public void onCreate() {
        safeCheck();
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleObserver());
    }

    /**
     * 安全检查，用于debug模式下对线程、vim 的检查策略
     */
    private void safeCheck() {
        if (BuildConfig.DEBUG) {
            // 线程检查策略
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads() // 读操作
                    .detectDiskWrites() // 写操作
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()// sqlite 对象泄露
                    .detectLeakedClosableObjects()// 未关闭的closable 对象
                    .penaltyLog()// 违规打印日志
                    .penaltyDeath()// 违规崩溃
                    .build());
        }
    }

}
