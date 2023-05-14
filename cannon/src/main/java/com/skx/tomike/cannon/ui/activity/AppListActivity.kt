package com.skx.tomike.cannon.ui.activity

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.IRepository
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.common.utils.AppUtils
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_PACKAGE_LIST
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers


/**
 * 描述 : app安装列表
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/4/10 11:49 下午
 */
@Route(path = ROUTE_PATH_PACKAGE_LIST)
class AppListActivity : SkxBaseActivity<AppListViewModel>() {

    private var mLoadingView: View? = null
    private var mAdapter: AppInfoAdapter? = null


    override fun initParams() {
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("APP安装列表").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_app_list
    }

    override fun initView() {
        val mRv = findViewById<RecyclerView>(R.id.rv_appList_apps)
        mRv.layoutManager = LinearLayoutManager(this)
        mAdapter = AppInfoAdapter()
        mRv.adapter = mAdapter

        mLoadingView = findViewById(R.id.rl_appList_loading)
        mLoadingView?.visibility = View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel.getAppList(mActivity)
    }

    override fun subscribeEvent() {
        super.subscribeEvent()
        mViewModel.getAppsInfoLiveData().observe(this, {
            mAdapter?.setAppInfoList(it)
            mLoadingView?.visibility = View.GONE
        })
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
        private val tvAppVersionName: TextView = itemView.findViewById(R.id.tv_appInfo_appVersionName)

        fun bindAppInfo(appInfo: AppInfo) {
            appInfo.icon?.run {
                ivIcon.setImageDrawable(this)
            }
            appInfo.appName?.run {
                tvAppName.text = this
            }
            appInfo.versionName?.run {
                tvAppVersionName.text = this
            }
        }
    }

    data class AppInfo(
            var uid: Int,
            val appName: String?,
            val packageName: String?,
            val versionName: String?,
            val icon: Drawable?)

}

class AppListViewModel(@NonNull application: Application) : BaseViewModel<IRepository>(application) {

    private val mAppsInfoLiveData: MutableLiveData<MutableList<AppListActivity.AppInfo>> = MutableLiveData()

    fun getAppsInfoLiveData(): LiveData<MutableList<AppListActivity.AppInfo>> {
        return mAppsInfoLiveData
    }

    fun getAppList(context: Context) {
        Observable.create<MutableList<AppListActivity.AppInfo>> { emitter ->


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
            val packageManager = context.packageManager
            val result: MutableList<AppListActivity.AppInfo> = mutableListOf()
            AppUtils.getInstalledPackages(context, false)?.run {
                for (p in this) {
                    val bean = AppListActivity.AppInfo(0,
                            packageManager.getApplicationLabel(p.applicationInfo).toString(),
                            p.packageName,
                            p.versionName,
                            p.applicationInfo.loadIcon(packageManager)
                    )
                    // 判断是否是属于系统的apk
                    if (p.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0) {
                        // 系统应用
                        Log.e("AppListActivity", "packageName=${p.packageName}, appName=${bean.appName}, 系统应用？yes")
                    } else {
                        Log.e("AppListActivity", "packageName=${p.packageName}, appName=${bean.appName}, 系统应用? no")
                        result.add(bean)
                    }
                }
            }
            emitter?.onNext(result)
            emitter?.onComplete()
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mAppsInfoLiveData.postValue(it)
                }
    }
}