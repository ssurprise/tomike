package com.skx.tomike.cannon.ui.activity

import android.content.pm.PackageManager
import android.os.Build
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
import com.skx.common.utils.DataTimeUtils.convert2String
import com.skx.common.utils.KeyboardTool
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

    private var mAppValue = ""

    override fun initParams() {}

    override fun layoutId(): Int {
        return R.layout.activity_app_information
    }

    override fun configHeaderTitle(): TitleConfig? {
        return TitleConfig.Builder().setTitleText("设备/应用信息").create()
    }

    override fun initView() {
        val ivAppIcon = findViewById<ImageView>(R.id.tv_appInfo_icon)
        val tvAppName = findViewById<TextView>(R.id.tv_appInfo_appName)
        val tvVersionName = findViewById<TextView>(R.id.tv_appInfo_versionName)
        val tvVersionCode = findViewById<TextView>(R.id.tv_appInfo_versionCode)
        val tvInstallTime = findViewById<TextView>(R.id.tv_appInfo_installTime)
        val tvUpdateTime = findViewById<TextView>(R.id.tv_appInfo_updateTime)
        val tvPermissions = findViewById<TextView>(R.id.tv_appInfo_permissions)

        findViewById<EditText>(R.id.et_appInfo_value).addTextChangedListener(object : TextWatcher {
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

            //获取包管理器
            val pm = packageManager
            try {
                //获取包信息
                val pkgInfo = pm.getPackageInfo(mAppValue, 0)

                //app icon
                ivAppIcon.setImageDrawable(pkgInfo.applicationInfo.loadIcon(pm))

                // app 名称
                tvAppName.text = String.format(
                    "名称：%s",
                    pm.getApplicationLabel(pkgInfo.applicationInfo).toString()
                )
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    tvVersionCode.text = String.format("versionCode：%s", pkgInfo.longVersionCode)
                } else {
                    tvVersionCode.text = String.format("versionCode：%s", pkgInfo.versionCode)
                }
                tvVersionName.text = String.format("versionName：%s", pkgInfo.versionName)
                tvInstallTime.text = String.format(
                    "安装时间：%s",
                    convert2String(pkgInfo.firstInstallTime, "yyyy-MM-dd hh:mm:ss")
                )
                tvUpdateTime.text = String.format(
                    "更新时间：%s",
                    convert2String(pkgInfo.lastUpdateTime, "yyyy-MM-dd hh:mm:ss")
                )

                // 用到的权限
                val permissionPkgInfo = pm.getPackageInfo(mAppValue, PackageManager.GET_PERMISSIONS)
                permissionPkgInfo.requestedPermissions?.forEach { name ->
                    tvPermissions.append("-$name\n")
                }
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
                ToastTool.showToast(mActivity, "没有找到指定包名的App")
            }
        }
    }
}