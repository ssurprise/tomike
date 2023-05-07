package com.skx.common.utils

import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager

/**
 * 描述 : App/Pkg 工具类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/5/7 4:59 下午
 */
object AppUtils {


    /**
     * 获取用户安装的所有apk列表。
     *
     * 需要申明权限：android.permission.QUERY_ALL_PACKAGES
     */
    fun getInstalledPackages(ctx: Context, isFilterSystem: Boolean = true): List<PackageInfo>? {

        val packageManager = ctx.packageManager
        val pkgList = packageManager.getInstalledPackages(0)

        return pkgList.filter {
            val flags = it.applicationInfo.flags
            flags and ApplicationInfo.FLAG_SYSTEM == 0 || !isFilterSystem
        }
    }

    /**
     * 获取用户应用使用统计信息。
     *
     * 需要申明权限：android.permission.PACKAGE_USAGE_STATS
     */
    fun getUsageStatsList(ctx: Context, beginTime: Long, endTime: Long): List<UsageStats> {
        val manager = ctx.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        return manager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,
                beginTime, endTime)
    }

    /**
     * 判断是否是系统应用
     *
     * @param context 上下文
     * @param packageName 目标应用包名
     * @return true:系统应用
     */
    fun isSystemApp(context: Context, packageName: String): Boolean {
        /*
        参考：com.android.server.pm.PackageManagerService#isSystemApp
        private static boolean isSystemApp(PackageSetting ps) {
            return (ps.pkgFlags & ApplicationInfo.FLAG_SYSTEM) != 0;
        }
         */
        val packageManager = context.packageManager
        try {
            val packageInfo: PackageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_CONFIGURATIONS)
            return (packageInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace();
        }
        return false
    }


//    fun getAppEnter(context: Context, packageName: String): String {
//        var mainAct = ""
//        try {
//            val intent = Intent().apply {
//                action = Intent.ACTION_MAIN
//                addCategory(Intent.CATEGORY_LAUNCHER)
//            }
//            val list =
//                    context.packageManager.queryIntentActivities(intent, PackageManager.GET_ACTIVITIES)
//
//            list.forEach {
//                if (TextUtils.equals(it.activityInfo.packageName, packageName)) {
//                    mainAct = it.activityInfo.name
//                }
//            }
//        } catch (ex: Exception) {
//
//        } finally {
//            return mainAct
//        }
//    }
}