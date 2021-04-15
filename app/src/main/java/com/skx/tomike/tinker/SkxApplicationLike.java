package com.skx.tomike.tinker;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.entry.DefaultApplicationLike;
import com.tencent.tinker.lib.listener.DefaultPatchListener;
import com.tencent.tinker.lib.listener.PatchListener;
import com.tencent.tinker.lib.patch.AbstractPatch;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.reporter.DefaultLoadReporter;
import com.tencent.tinker.lib.reporter.DefaultPatchReporter;
import com.tencent.tinker.lib.reporter.LoadReporter;
import com.tencent.tinker.lib.reporter.PatchReporter;
import com.tencent.tinker.lib.service.DefaultTinkerResultService;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.shareutil.ShareConstants;

@DefaultLifeCycle(application = "com.skx.tomike.SkxApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL)
class SkxApplicationLike extends DefaultApplicationLike {

    private final String TAG = "Tinker.ApplicationLike";

    public SkxApplicationLike(Application application,
                              int tinkerFlags,
                              boolean tinkerLoadVerifyFlag,
                              long applicationStartElapsedTime,
                              long applicationStartMillisTime,
                              Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime,
                applicationStartMillisTime, tinkerResultIntent);
        Log.d(TAG, "SkxApplicationLike -> constructor");
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        Log.d(TAG, "SkxApplicationLike -> onBaseContextAttached");

        //you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

        LoadReporter loadReporter = new DefaultLoadReporter(base);
        PatchReporter patchReporter = new DefaultPatchReporter(base);
        PatchListener patchListener = new DefaultPatchListener(base);
        AbstractPatch upgradePatchProcessor = new UpgradePatch();

        TinkerInstaller.install(this,
                loadReporter,//加载合成的包的报告类
                patchReporter,//打修复包过程中的报告类
                patchListener,//对修复包最开始的检查
                DefaultTinkerResultService.class, //patch包合成完成的后续操作服务
                upgradePatchProcessor);//生成一个新的patch合成包
    }
}
