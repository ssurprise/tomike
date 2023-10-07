package com.skx.common.utils

import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.text.TextUtils

/**
 * 描述 : App/Pkg 工具类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/5/7 4:59 下午
 */
object AppUtils {

    /**
     * 获取App 包名
     * @param context 上下文
     * @return app名称
     */
    fun getAppName(context: Context): String {
        //获取包管理器
        val pm: PackageManager = context.packageManager
        return try {
            //获取包信息
            val packageInfo: PackageInfo = pm.getPackageInfo(context.packageName, 0)
            val labelRes = packageInfo.applicationInfo.labelRes
            context.resources.getString(labelRes)
            //        pm.getApplicationLabel(packageInfo.applicationInfo).toString()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            ""
        }
    }

    /**
     * 获取App 包名
     */
    fun getAppName(context: Context, packageName: String): String {
        //获取包管理器
        val pm: PackageManager = context.packageManager
        return try {
            //获取包信息
            val packageInfo: PackageInfo = pm.getPackageInfo(packageName, 0)
            return pm.getApplicationLabel(packageInfo.applicationInfo).toString()
            /*
           注：不能使用下面的方式，因为上下文对应的resources 没有包含对应的资源，此种方式适用于获取本App的信息，不适用于获取第三方APP的信息
           val labelRes = packageInfo.applicationInfo.labelRes
            context.resources.getString(labelRes)
            */
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            ""
        }
    }

    /**
     * Array of all <uses-permission> tags included under <manifest>, or null if there were none.
     * This is only filled in if the flag PackageManager.GET_PERMISSIONS was set.
     * This list includes all permissions requested, even those that were not granted or known by the system at install time.
     *
     * @param context 上下文
     * @param packageName app包名
     * @return 清单文件中声明使用的所有权限，如果没有则返回null。
     */
    fun getRequestedPermissions(context: Context, packageName: String): Array<String>? {
        val pm: PackageManager = context.packageManager
        return try {
            return pm.getPackageInfo(
                packageName,
                PackageManager.GET_PERMISSIONS
            ).requestedPermissions
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            null
        }
    }


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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            val manager = ctx.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
            return manager.queryUsageStats(
                UsageStatsManager.INTERVAL_DAILY,
                beginTime, endTime
            )
        }
        return emptyList()
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
            val packageInfo: PackageInfo =
                packageManager.getPackageInfo(packageName, PackageManager.GET_CONFIGURATIONS)
            return (packageInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return false
    }

    /**
     * 判断指定包名的App是否已经安装
     *
     * @param context 上下文
     * @param packageName 包名
     * @return true:已经安装
     */
    fun isApkInstalled(context: Context, packageName: String?): Boolean {
        if (TextUtils.isEmpty(packageName)) {
            return false
        }
        var isInstalled = false
        try {
            context.packageManager.getPackageInfo(
                packageName!!,
                PackageManager.GET_ACTIVITIES
            )?.apply {
                isInstalled = true
            }
        } catch (ex: PackageManager.NameNotFoundException) {
            ex.printStackTrace()
        }
        return isInstalled
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