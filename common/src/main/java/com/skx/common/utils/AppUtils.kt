package com.skx.common.utils

import android.app.usage.StorageStats
import android.app.usage.StorageStatsManager
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.*
import android.os.Build
import android.os.storage.StorageManager
import android.text.TextUtils
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.annotation.WorkerThread
import java.lang.reflect.Method
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean

/**
 * 描述 : App/Pkg 工具类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/5/7 4:59 下午
 */
object AppUtils {
    const val TAG = "AppUtils"

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {// Android 5.1
            val manager = ctx.getSystemService(Context.USAGE_STATS_SERVICE) as? UsageStatsManager
            return manager?.queryUsageStats(
                UsageStatsManager.INTERVAL_DAILY,
                beginTime, endTime
            ) ?: kotlin.run { emptyList() }
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAppCacheSizeO(context: Context, packageName: String?): Long {
        if (TextUtils.isEmpty(packageName)) return -1
        val storageStatsManager =
            context.applicationContext.getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager
        val storageManager =
            context.applicationContext.getSystemService(Context.STORAGE_SERVICE) as StorageManager

        var uid = -1
        try {
            uid = context.packageManager.getApplicationInfo(
                packageName!!,
                PackageManager.GET_META_DATA
            ).uid
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        Log.d(TAG, "#getAppCacheSizeO. packageName=${packageName}, uid=$uid")
        if (uid == -1) {
            Log.d(TAG, "#getAppCacheSizeO. uid=-1 return")
            return -1
        }
        Log.d(TAG, "#getAppCacheSizeO. storageVolumes size=" + storageManager.storageVolumes.size)
        var totalCacheSize: Long = 0
        var storageStats: StorageStats?
        for (item in storageManager.storageVolumes) {
            val uuid = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                Log.d(TAG, "#getAppCacheSizeO. uuid=${item.storageUuid}")
                item.storageUuid ?: StorageManager.UUID_DEFAULT
            } else if (!TextUtils.isEmpty(item.uuid)) {
                Log.d(TAG, "#getAppCacheSizeO. uuid=${item.uuid}")
                UUID.fromString(item.uuid)
            } else {
                Log.d(TAG, "#getAppCacheSizeO. use default = ${StorageManager.UUID_DEFAULT}")
                StorageManager.UUID_DEFAULT
            }
            try {
                storageStats = storageStatsManager.queryStatsForUid(uuid, uid)
                totalCacheSize += storageStats.cacheBytes
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return totalCacheSize
    }

    fun getAppCacheSize(context: Context, packageName: String?): Long {
        var totalCacheSize: Long = 0
        val method: Method = PackageManager::class.java.getMethod(
            "getPackageSizeInfo",
            String::class.java,
            IPackageStatsObserver::class.java
        )
        val finish = AtomicBoolean(false)
        method.invoke(context.packageManager, packageName, object : IPackageStatsObserver.Stub() {
            override fun onGetStatsCompleted(pStats: PackageStats?, succeeded: Boolean) {
                totalCacheSize += pStats?.cacheSize ?: 0
                Log.d(TAG, "#getAppCacheSize. totalCacheSize=${totalCacheSize}")
                finish.compareAndSet(false, true)
            }
        })
        while (!finish.get()) {
            Thread.sleep(100)
        }
        return totalCacheSize
    }

    @WorkerThread
    fun getCacheSize(context: Context, packageName: String?, unit: String = "MB"): String {
        if (TextUtils.isEmpty(packageName)) {
            return "unknown"
        }
        var cacheSize = 0L
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 应用程序缓存数据的大小，包括存储在 Context.getCacheDir() 和 Context.getCodeCacheDir() 下的文件.
            // 即 data/data/package-name/ 下关于缓存相关的目录
            cacheSize = getAppCacheSizeO(context, packageName)
        } else {
            cacheSize = getAppCacheSize(context, packageName)
        }
        if (cacheSize > 0) {
            return (cacheSize / (1024 * 1024)).toString()
        }
        return "unknown"
    }
//    StorageStats storageStats = storageStatsManager.queryStatsForPackage(uuid, packageName, userHandle);
//应用程序的大小。这包括 APK 文件、优化的编译器输出和解压的原生库
// long appBytes = storageStats.getAppBytes();
// 应用程序缓存数据的大小。这包括存储在 Context.getCacheDir() 和 Context.getCodeCacheDir() 下的文件
// long cacheBytes = storageStats.getCacheBytes();
// 应用程序所有数据的大小。这包括存储在 Context.getDataDir()、Context.getCacheDir()、Context.getCodeCacheDir() 下的文件
// long dataBytes = storageStats.getDataBytes();
// 主外部共享存储中所有缓存数据的大小。这包括存储在 Context.getExternalCacheDir() 下的文件
// long externalCacheBytes = storageStats.getExternalCacheBytes();
// } catch (IOException | PackageManager.NameNotFoundException e) {e.printStackTrace();}


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