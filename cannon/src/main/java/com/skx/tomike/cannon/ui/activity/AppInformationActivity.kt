package com.skx.tomike.cannon.ui.activity

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.common.utils.AppUtils
import com.skx.common.utils.DataTimeUtils.convert2String
import com.skx.common.utils.KeyboardTool
import com.skx.common.utils.MemoryUtils
import com.skx.common.utils.ToastTool
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_APP_INFO

/**
 * 描述 : App信息
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/8/5 4:44 PM
 */
@Route(path = ROUTE_PATH_APP_INFO)
class AppInformationActivity : SkxBaseActivity<BaseViewModel<*>>() {

    private val mEtAppPkgName: EditText by lazy { findViewById(R.id.et_appInfo_value) }
    private val mIvAppIcon: ImageView by lazy { findViewById(R.id.iv_appInfo_icon) }
    private val mTvAppName: TextView by lazy { findViewById(R.id.tv_appInfo_appName) }
    private val mTvVersionName: TextView by lazy { findViewById(R.id.tv_appInfo_versionName) }
    private val mTvVersionCode: TextView by lazy { findViewById(R.id.tv_appInfo_versionCode) }
    private val mTvInstallTime: TextView by lazy { findViewById(R.id.tv_appInfo_installTime) }
    private val mTvCacheSize: TextView by lazy { findViewById(R.id.tv_appInfo_cacheSize) }
    private val mTvUpdateTime: TextView by lazy { findViewById(R.id.tv_appInfo_updateTime) }
    private val mTvPermissions: TextView by lazy { findViewById(R.id.tv_appInfo_permissions) }

    private var mAppValue = "com.skx.tomike"

    override fun initParams() {}

    override fun layoutId(): Int {
        return R.layout.activity_app_information
    }

    override fun configHeaderTitle(): TitleConfig? {
        return TitleConfig.Builder().setTitleText("App信息").create()
    }

    override fun initView() {
        mEtAppPkgName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                mAppValue = s.toString().trim { it <= ' ' }
            }
        })
        findViewById<View>(R.id.btn_appInfo_start).setOnClickListener {
            if (TextUtils.isEmpty(mAppValue)) {
                ToastTool.showToast(mActivity, "请输入要查询的App包名")
                return@setOnClickListener
            }
            KeyboardTool.getInstances(mActivity).hideKeyboard(it)
            check()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mEtAppPkgName.setText(mAppValue)
        check()
    }

    private fun check() {
        //获取包管理器
        val pm = packageManager
        try {
            //获取包信息
            val pkgInfo = pm.getPackageInfo(mAppValue, 0)

            //app icon
            mIvAppIcon.setImageDrawable(pkgInfo.applicationInfo.loadIcon(pm))

            // app 名称
            mTvAppName.text = String.format(
                "名称：%s",
                pm.getApplicationLabel(pkgInfo.applicationInfo).toString()
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                mTvVersionCode.text = String.format("versionCode：%s", pkgInfo.longVersionCode)
            } else {
                mTvVersionCode.text = String.format("versionCode：%s", pkgInfo.versionCode)
            }
            mTvVersionName.text = String.format("versionName：%s", pkgInfo.versionName)
            mTvInstallTime.text = String.format(
                "安装时间：%s",
                convert2String(pkgInfo.firstInstallTime, "yyyy-MM-dd hh:mm:ss")
            )
            mTvUpdateTime.text = String.format(
                "更新时间：%s",
                convert2String(pkgInfo.lastUpdateTime, "yyyy-MM-dd hh:mm:ss")
            )

            // 缓存大小
            mTvCacheSize.text = String.format("缓存大小：%sMB", AppUtils.getCacheSize(this, mAppValue))

            mTvPermissions.text = ""
            // 用到的权限
            val permissionPkgInfo = pm.getPackageInfo(mAppValue, PackageManager.GET_PERMISSIONS)
            permissionPkgInfo.requestedPermissions?.forEach { name ->
                mTvPermissions.append("-$name\n")
            }

            MemoryUtils.getAppMemorySize(this)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            ToastTool.showToast(mActivity, "没有找到指定包名的App")
        }
    }
}