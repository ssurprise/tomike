package com.skx.tomike.cannon.ui.activity

import android.Manifest
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.common.permission.PermissionController
import com.skx.common.permission.PermissionResultListener
import com.skx.common.utils.AppUtils
import com.skx.common.utils.ToastTool
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_APP_USAGE_STATS
import java.util.*


/**
 * 描述 : app使用次数
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/4/10 11:49 下午
 */
@Route(path = ROUTE_PATH_APP_USAGE_STATS)
class AppUsageStatsActivity : SkxBaseActivity<BaseViewModel>() {

    private val mData: MutableList<AppInfo> = mutableListOf()


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

                    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
                    override fun onSucceed(grantPermissions: List<String>?) {
                        getPackages()
                    }

                    override fun onFailed(deniedPermissions: List<String>?) {
                        ToastTool.showToast(this@AppUsageStatsActivity, "当前没有'使用情况访问权限'！")
                    }
                })
                .request()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private fun getPackages() {
        mData.clear()
        val allAppInfo: MutableList<AppInfo> = mutableListOf()
        AppUtils.getInstalledPackages(this)?.forEach {
            val appInfo = AppInfo(packageManager.getApplicationLabel(it.applicationInfo).toString(),
                    0,
                    it.packageName,
                    it.applicationInfo.loadIcon(packageManager)
            )
            allAppInfo.add(appInfo)
        }

        getUsageList(applicationContext)?.forEach {
            if (!AppUtils.isSystemApp(applicationContext, it.packageName)) {
                addRecommendApp(it, mData, allAppInfo)
            }
        }
        mData.sort()
//        adapter.setDatas(mData)
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    fun getUsageList(context: Context): List<UsageStats>? {
        val manager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

        val calendar: Calendar = Calendar.getInstance()
        val endTime: Long = calendar.timeInMillis

        calendar.add(Calendar.DAY_OF_WEEK, -2)
        val startTime: Long = calendar.timeInMillis

        return manager.queryUsageStats(UsageStatsManager.INTERVAL_YEARLY, startTime, endTime)
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
//            usageStats.getFirstTimeStamp();//获取第一次运行的时间
//            usageStats.getLastTimeStamp();//获取最后一次运行的时间
//            usageStats.getTotalTimeInForeground();//获取总共运行的时间


            // 获取应用启动次数，UsageStats未提供方法来获取，只能通过反射来拿到
            val count = stats.javaClass.getDeclaredField("mLaunchCount").get(stats) as Int
            Log.e(TAG, stats.packageName
                    + " launchCount=${count}"
                    + " FirstTimeStamp=${stats.firstTimeStamp}"
                    + " LastTimeStamp=${stats.lastTimeStamp}"
                    + " totalTimeInForeground=${stats.totalTimeInForeground}"
            )

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


    class AppInfo(val name: String?, var launchCount: Int, val packageName: String?, val icon: Drawable) : Comparable<Any?> {

        override operator fun compareTo(o: Any?): Int {
            return if (o is AppInfo) {
                if (launchCount > o.launchCount) -1 else 1
            } else 0
        }
    }

}