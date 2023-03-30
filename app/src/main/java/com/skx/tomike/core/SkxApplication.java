package com.skx.tomike.core;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.StrictMode;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.mmkv.BuildConfig;

public class SkxApplication extends Application {

    @Override
    public void onCreate() {
        safeCheck();
        super.onCreate();
        init();
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

    private void init() {
        routerInit();
        // 注册生命周期管理栈
        registerActivityLifecycleCallbacks(new ActivityLifecycleObserver());
        // 注册home键监听
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(new HomeKeyReceiver(), filter);
    }

    /**
     * 路由初始化 - 阿里路由
     * 注：后续会自己实现路线功能
     */
    private void routerInit() {
        if (BuildConfig.DEBUG) {
            // 这两行必须写在init之前，否则这些配置在init过程中将无效.
            // 打印日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
            // 打印日志的时候打印线程堆栈
            ARouter.printStackTrace();
        }
        ARouter.init(this);
    }
}
