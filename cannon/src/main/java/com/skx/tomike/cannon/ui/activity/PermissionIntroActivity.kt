package com.skx.tomike.cannon.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.core.app.ActivityCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTER_GROUP
import com.skx.tomike.cannon.ROUTE_PATH_PERMISSION

/**
 * 6.0 权限介绍
 */
@Route(path = ROUTE_PATH_PERMISSION, group = ROUTER_GROUP)
class PermissionIntroActivity : SkxBaseActivity<BaseViewModel?>() {

    private var mActivityResultLauncher: ActivityResultLauncher<Array<String>>? = null
    private val mTvLogcat by lazy {
        findViewById<TextView>(R.id.tv_permission_logcat)
    }

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("权限管理").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_permission_intro
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        mActivityResultLauncher = registerForActivityResult(
            RequestMultiplePermissions()
        ) { result: Map<String?, Boolean?> ->
            // 定位权限结果
            val locationLocation = result[Manifest.permission.ACCESS_FINE_LOCATION]
            mTvLogcat.append(Manifest.permission.ACCESS_FINE_LOCATION + "：" + locationLocation + "\n")
            Log.e(TAG, Manifest.permission.ACCESS_FINE_LOCATION + "：" + locationLocation)

            // 摄像头权限结果
            val cameraResult = result[Manifest.permission.CAMERA]
            mTvLogcat.append(Manifest.permission.CAMERA + "：" + cameraResult + "\n\n\n")
            Log.e(TAG, Manifest.permission.CAMERA + "：" + cameraResult)
        }
    }

    @SuppressLint("SetTextI18n")
    fun onByPressed(view: View?) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                /*
                 * 这里权限模式
                 */
//                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
//                    // 解释为什么需要定位权限之类的
//                } else {
//                    activityResultLauncher.launch( new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
//                            Manifest.permission.CAMERA});
//                }
                mTvLogcat.append("申请" + Manifest.permission.ACCESS_FINE_LOCATION + " 权限\n")
                Log.e(TAG, "申请" + Manifest.permission.ACCESS_FINE_LOCATION + " 权限")

                mTvLogcat.append("申请" + Manifest.permission.CAMERA + " 权限\n")
                Log.e(TAG, "申请" + Manifest.permission.CAMERA + " 权限")

                mActivityResultLauncher?.launch(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA)
                )
                /*
                 * 如果要用意图模式的话，就不需要用权限模式了，直接跳转到系统设置页面，
                 * 让用户自己控制权限的授权与否，app只承担了一个引导作用
                 */
            } else {
                mTvLogcat.append("当前已有" + Manifest.permission.ACCESS_FINE_LOCATION + " 权限\n")
                Log.e(TAG, "当前已有" + Manifest.permission.ACCESS_FINE_LOCATION + " 权限")
                // 获得定位信息的code
            }
        } else {
            mTvLogcat.text = "6.0以下无需动态申请权限\n"
            Log.e(TAG, "6.0以下无需动态申请权限")
        }
    }
}