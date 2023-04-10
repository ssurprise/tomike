package com.skx.tomike.cannon.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Service
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.common.permission.PermissionController
import com.skx.common.permission.PermissionResultListener
import com.skx.common.utils.ToastTool
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_APP_USAGE_STATS


/**
 * 描述 : app使用次数
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/4/10 11:49 下午
 */
@Route(path = ROUTE_PATH_APP_USAGE_STATS)
class AppUsageStatsActivity : SkxBaseActivity<BaseViewModel>() {


    override fun initParams() {
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("APP使用次数统计").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_app_usage_stats
    }

    override fun initView() {
        PermissionController.Builder(this)
                .permission(Manifest.permission.PACKAGE_USAGE_STATS)
                .associateDefaultTip()
                .callback(object : PermissionResultListener {
                    override fun onSucceed(grantPermissions: List<String>?) {
                        getUsageList(this@AppUsageStatsActivity)?.forEach {
                            Log.e(TAG, it?.packageName?:"")
                        }
                    }

                    override fun onFailed(deniedPermissions: List<String>?) {
                        ToastTool.showToast(this@AppUsageStatsActivity, "当前没有'使用情况访问权限'！")
                    }
                })
                .request()
    }


    fun getAllAppInfo(ctx: Context, isFilterSystem: Boolean): ArrayList<AppInfo?>? {
        val appBeanList: ArrayList<AppInfo?> = ArrayList()
        var bean: AppInfo? = null
        val packageManager = ctx.packageManager
        val list = packageManager.getInstalledPackages(0)
        for (p in list) {
            bean = AppInfo(packageManager.getApplicationLabel(p.applicationInfo).toString(),
                    0,
                    p.packageName,
                    p.applicationInfo.loadIcon(packageManager)
            )
            val flags = p.applicationInfo.flags
            // 判断是否是属于系统的apk
            if (flags and ApplicationInfo.FLAG_SYSTEM != 0 && isFilterSystem) {
            } else {
                appBeanList.add(bean)
            }
        }
        return appBeanList
    }


    @SuppressLint("WrongConstant")
    fun getUsageList(context: Context): List<UsageStats?>? {
        val manager = context.getSystemService("usagestats") as UsageStatsManager
        var endTime: Long = 0
        var startTime: Long = 0
        endTime = System.currentTimeMillis()
        startTime = System.currentTimeMillis() - (1000 * 60 * 60 * 24 * 7)//getZeroClockTimestamp(endTime)
        return manager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, startTime, endTime)
    }

    /**
     * 根据使用频次推荐app
     */
    private fun addRecommendApp(
            stats: UsageStats,
            localApps: MutableList<AppInfo>,
            appInfoList: List<AppInfo>
    ) {
        try {
            val count = stats.javaClass.getDeclaredField("mLaunchCount").getInt(stats)
            if (count != 0) {
                for (i in appInfoList.indices) {
                    if (stats.packageName == appInfoList[i].packageName) {
                        appInfoList[i].launchCount = count
                        localApps.add(appInfoList[i])
                    }
                }
            }
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }
    }

//    private fun getPackages() {
//        datas.clear()
//        val allAppInfo = AppHelper.getAllAppInfo(this, true)
//
//        val usagList = AppHelper.getUsagList(applicationContext)
//        usagList.forEach {
//            if (!AppHelper.isSystemApp(applicationContext, it.packageName)) {
//                addRecommendApp(it, datas, allAppInfo)
//            }
//        }
//        datas.sort()
//        adapter.setDatas(datas)
//    }


    class AppInfo(val name: String, var launchCount: Int, val packageName: String, val icon: Drawable) : Comparable<Any?> {

        override operator fun compareTo(o: Any?): Int {
            return if (o is AppInfo) {
                if (launchCount > o.launchCount) -1 else 1
            } else 0
        }
    }

}