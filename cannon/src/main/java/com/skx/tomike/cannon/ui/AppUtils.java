package com.skx.tomike.cannon.ui;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import androidx.annotation.Nullable;

public class AppUtils {

    private AppUtils() {
    }

    private static class Singleton {
        private static final AppUtils INSTANCE = new AppUtils();
    }

    public static AppUtils getInstance() {
        return Singleton.INSTANCE;
    }

    /**
     * desc 获取app包名
     */
    public String getAppPackage() {
        String packageName = AppContext.getInstance().getAppContext().getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            try {
                PackageManager packageManager = AppContext.getInstance().getAppContext().getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(
                        AppContext.getInstance().getAppContext().getPackageName(), 0);
                packageName = packageInfo.packageName;
            } catch (Exception e) {
                packageName = "com.example.tinkerdemo";
                e.printStackTrace();
            }
        }
        return packageName;
    }

    @Nullable
    public String getAppChannel() {
        Context context = AppContext.getInstance().getAppContext();
        return ChannelUtils.getChannel(context, "stinkerapp");
    }
}
