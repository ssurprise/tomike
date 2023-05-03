package com.skx.tomike.cannon.ui.activity

import android.content.Context
import android.content.pm.ApplicationInfo
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_APP_LIST


/**
 * 描述 : app列表
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/4/10 11:49 下午
 */
@Route(path = ROUTE_PATH_APP_LIST)
class AppListActivity : SkxBaseActivity<BaseViewModel>() {


    override fun initParams() {
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("APP列表").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_app_list
    }

    override fun initView() {
        val mRv = findViewById<RecyclerView>(R.id.rv_appList_apps)
        mRv.layoutManager = LinearLayoutManager(this)
        val adapter = AppInfoAdapter()
        mRv.adapter = adapter
        val data = getAllAppInfo(this@AppListActivity)
        adapter.setAppInfoList(data)
    }


    private fun getAllAppInfo(ctx: Context): MutableList<AppInfo> {
        val appInfoList: MutableList<AppInfo> = mutableListOf()
        val packageManager = ctx.packageManager

        val start = System.currentTimeMillis()
        val list = packageManager.getInstalledPackages(0)
        val end = System.currentTimeMillis()
        Log.e(TAG, "耗时：=${end - start}")
        /* 注意
        getInstalledPackages 方法并不耗时。
        下面的两个方法比较耗时：
        packageManager.getApplicationLabel(p.applicationInfo) - 首次获取能超过2000ms
        p.applicationInfo.loadIcon(packageManager) - 首次获取能超过2000ms
            2023-05-04 01:01:22.760 6998-6998/com.skx.tomike E/AppListActivity: 耗时：=24
            2023-05-04 01:01:25.750 6998-7033/com.skx.tomike E/HiView.HiEvent: length is 0 or exceed MAX: 1024
            2023-05-04 01:01:25.807 6998-6998/com.skx.tomike E/AppListActivity: 耗时2：=3071
            2023-05-04 01:01:26.335 6998-6998/com.skx.tomike E/RtgSchedManager: endActivityTransaction: margin state not match
            2023-05-04 01:01:39.984 6998-6998/com.skx.tomike E/AppListActivity: 耗时：=14
            2023-05-04 01:01:40.851 6998-6998/com.skx.tomike E/AppListActivity: 耗时2：=881
            2023-05-04 01:01:43.662 6998-6998/com.skx.tomike E/AppListActivity: 耗时：=13
            2023-05-04 01:01:44.502 6998-6998/com.skx.tomike E/AppListActivity: 耗时2：=853
            2023-05-04 01:01:47.292 6998-6998/com.skx.tomike E/AppListActivity: 耗时：=14
            2023-05-04 01:01:48.114 6998-6998/com.skx.tomike E/AppListActivity: 耗时2：=836

         */
        for (p in list) {
            val bean = AppInfo(0,
                    packageManager.getApplicationLabel(p.applicationInfo).toString(),
                    p.packageName,
                    p.applicationInfo.loadIcon(packageManager)
            )
            // 判断是否是属于系统的apk
            if (p.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0) {
                // 系统应用
                Log.e(TAG, "packageName=${p.packageName}, name=${bean.name}, 系统应用？yes")
            } else {
                Log.e(TAG, "packageName=${p.packageName}, name=${bean.name}, 系统应用? no")
                appInfoList.add(bean)
            }
        }
        val end2 = System.currentTimeMillis()
        Log.e(TAG, "耗时2：=${end2 - start}")
        return appInfoList
    }

    class AppInfoAdapter : RecyclerView.Adapter<AppInfoViewHolder>() {

        private val mAppInfoList: MutableList<AppInfo> = mutableListOf()

        fun setAppInfoList(appInfoList: MutableList<AppInfo>?) {
            mAppInfoList.clear()
            appInfoList?.run {
                mAppInfoList.addAll(this)
            }
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppInfoViewHolder {
            return AppInfoViewHolder(LayoutInflater.from(parent.context).inflate(
                    R.layout.adapter_app_info, parent, false
            ))
        }

        override fun onBindViewHolder(holder: AppInfoViewHolder, position: Int) {
            holder.bindAppInfo(mAppInfoList[position])
        }

        override fun getItemCount(): Int = mAppInfoList.size
    }

    class AppInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivIcon: ImageView = itemView.findViewById(R.id.iv_appInfo_icon)
        private val tvAppName: TextView = itemView.findViewById(R.id.tv_appInfo_appName)

        fun bindAppInfo(appInfo: AppInfo) {
            appInfo.icon?.run {
                ivIcon.setImageDrawable(this)
            }
            appInfo.name?.run {
                tvAppName.text = this
            }
        }
    }

    data class AppInfo(
            var uid: Int,
            val name: String?,
            val packageName: String?,
            val icon: Drawable?)

}