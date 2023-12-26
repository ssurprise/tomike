package com.skx.tomike.cannon.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


/**
 * 描述 : app使用次数
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/4/10 11:49 下午
 */
@Route(path = ROUTE_PATH_APP_USAGE_STATS)
class AppUsageStatsActivity : SkxBaseActivity<BaseViewModel<*>>() {

    private var mAdapter: AppStatsInfoAdapter? = null
    private val mStatsInfoLiveData = MutableLiveData<MutableList<AppInfo>>()

    override fun initParams() {
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("APP使用次数统计").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_app_usage_stats
    }

    override fun initView() {
        val mRv = findViewById<RecyclerView>(R.id.rv_appList_apps)
        mRv.layoutManager = LinearLayoutManager(this)
        mAdapter = AppStatsInfoAdapter()
        mRv.adapter = mAdapter

        PermissionController.Builder(this)
            .permission(Manifest.permission.PACKAGE_USAGE_STATS)
            .associateDefaultTip()
            .callback(object : PermissionResultListener {

                @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
                override fun onSucceed(grantPermissions: List<String>?) {
                    getStats()
                }

                override fun onFailed(deniedPermissions: List<String>?) {
                    ToastTool.showToast(this@AppUsageStatsActivity, "当前没有'使用情况访问权限'！")
                    finish()
                }
            })
            .request()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mStatsInfoLiveData.observe(this) {
            mAdapter?.setData(it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private fun getStats() {
        GlobalScope.launch {
            val statsMap = TreeMap<String, AppInfo>()
            getUsageList(this@AppUsageStatsActivity)?.filter {
                AppUtils.isApkInstalled(this@AppUsageStatsActivity, it.packageName)
                        && !AppUtils.isSystemApp(this@AppUsageStatsActivity, it.packageName)
            }?.forEach {
                relatedLaunchCount(it, statsMap)
            }
            val sorted = statsMap.map { it.value }.sorted()
            val list = mutableListOf<AppInfo>().apply {
                addAll(sorted)
            }
            mStatsInfoLiveData.postValue(list)
        }
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
    private fun relatedLaunchCount(stats: UsageStats, map: TreeMap<String, AppInfo>) {
        try {
            /*
            usageStats.getFirstTimeStamp();//获取第一次运行的时间
            usageStats.getLastTimeStamp();//获取最后一次运行的时间
            usageStats.getTotalTimeInForeground();//获取总共运行的时间
             */
            // 获取应用启动次数，UsageStats未提供方法来获取，只能通过反射来拿到
            val count = stats.javaClass.getDeclaredField("mLaunchCount").get(stats) as Int
            Log.e(
                TAG, stats.packageName
                        + " launchCount=${count}"
                        + ", firstTimeStamp=${stats.firstTimeStamp}"
                        + ", lastTimeStamp=${stats.lastTimeStamp}"
                        + ", totalTimeInForeground=${stats.totalTimeInForeground}"
            )
            val packageInfo = packageManager.getPackageInfo(stats.packageName, 0)
            if (map.containsKey(stats.packageName)) {
                map[stats.packageName]?.run {
                    launchCount += count
                }
            } else {
                val applicationInfo = packageInfo.applicationInfo
                val appInfo = AppInfo(
                    packageManager.getApplicationLabel(applicationInfo).toString(),
                    count,
                    stats.packageName,
                    applicationInfo.loadIcon(packageManager),
                    packageInfo.versionName
                )
                map[stats.packageName] = appInfo
            }
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }
    }


    class AppInfo(
        val name: String?,
        var launchCount: Int,
        val packageName: String?,
        val icon: Drawable,
        val versionName: String?
    ) : Comparable<Any?> {

        override operator fun compareTo(other: Any?): Int {
            return if (other is AppInfo) {
                if (launchCount > other.launchCount) -1 else 1
            } else 0
        }
    }

    class AppStatsInfoAdapter : RecyclerView.Adapter<AppStatsInfoViewHolder>() {

        private val mAppInfoList: MutableList<AppInfo> = mutableListOf()

        @SuppressLint("NotifyDataSetChanged")
        fun setData(appInfoList: MutableList<AppInfo>?) {
            mAppInfoList.clear()
            appInfoList?.run {
                mAppInfoList.addAll(this)
            }
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppStatsInfoViewHolder {
            return AppStatsInfoViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.adapter_app_stats_info, parent, false
                )
            )
        }

        override fun onBindViewHolder(holder: AppStatsInfoViewHolder, position: Int) {
            holder.bindAppInfo(mAppInfoList[position])
        }

        override fun getItemCount(): Int = mAppInfoList.size
    }

    class AppStatsInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivIcon: ImageView = itemView.findViewById(R.id.iv_appStatsInfo_icon)
        private val tvAppName: TextView = itemView.findViewById(R.id.tv_appStatsInfo_appName)
        private val tvPackageName: TextView = itemView.findViewById(R.id.tv_appStatsInfo_pkgName)
        private val tvUseStats: TextView = itemView.findViewById(R.id.tv_appStatsInfo_useStats)
        private val tvVersionName: TextView =
            itemView.findViewById(R.id.tv_appStatsInfo_versionName)

        @SuppressLint("SetTextI18n")
        fun bindAppInfo(appInfo: AppInfo) {
            appInfo.icon.run {
                ivIcon.setImageDrawable(this)
            }
            appInfo.name?.run {
                tvAppName.text = this
            }
            appInfo.packageName?.run {
                tvPackageName.text = this
            }
            appInfo.versionName?.run {
                tvVersionName.text = this
            }
            appInfo.launchCount.run {
                tvUseStats.text = "最近使用${this}次"
            }
        }
    }
}