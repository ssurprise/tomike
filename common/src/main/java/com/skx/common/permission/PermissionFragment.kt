package com.skx.common.permission

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.skx.common.base.BaseFragment
import java.util.*

/**
 * 描述 : 权限
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/9/16 5:21 PM
 */
class PermissionFragment : BaseFragment(), PermissionNegotiate {

    /**
     * 用户提交的所有权限
     */
    private var mPermissions: List<String>? = null
    private var mCallback: PermissionResultListener? = null
    private var mReqPermissionTip: ReqPermissionTip? = null

    // 普通权限请求launcher
    private var mPermissionReqLauncher: ActivityResultLauncher<Array<String>>? = null

    // 打开APP 介绍页launcher
    private var mSettingLauncher: ActivityResultLauncher<Intent>? = null

    // 系统设置页launcher
    private var mSystemSettingLauncher: ActivityResultLauncher<Intent>? = null

    // 全局弹窗launcher
    private var mSystemAlertWindowLauncher: ActivityResultLauncher<Intent>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParams()
    }

    private fun initParams() {
        mPermissionReqLauncher = registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
        ) { result: Map<String?, Boolean?> ->
            // 注：这里之所有没有直接使用回调参数中的result 进行判断是因为有些特殊权限不会通过这里获取授权结果。
            val deniedArray = generateDeniedArray(mPermissions)
            // 1.全部同意
            if (deniedArray.isNullOrEmpty()) {
                dispatchResult(null)
                return@registerForActivityResult
            }
            // 2.拒绝的权限
            // 2.1 外部接管
            if (mReqPermissionTip != null) {
                PermLog.d("有权限请求失败，展示请求权限的原因，deniedArray=${deniedArray}")
                mReqPermissionTip?.showReqPermissionsReason(deniedArray, this)
                return@registerForActivityResult
            }
            // 2.2 外部不接管，走授权失败回调
            dispatchResult(deniedArray)
        }
        // 权限设置
        mSettingLauncher = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
        ) {
            PermLog.d("从权限设置页返回 resultCode:${it.resultCode}")
            val needRequest = generateDeniedArray(mPermissions)
            dispatchResult(needRequest)
        }
        // 系统权限设置
        mSystemSettingLauncher = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
        ) {
            specialPermissionHandling(WRITE_SETTINGS)
        }
        // 系统悬浮窗权限设置
        mSystemAlertWindowLauncher = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
        ) {
            specialPermissionHandling(SYSTEM_ALERT_WINDOW)
        }
    }

    fun reqPermissions(
            permissions: List<String>?,
            callback: PermissionResultListener?,
            reqPermissionTip: ReqPermissionTip?
    ) {
        if (permissions == null || permissions.isEmpty()) {
            PermLog.d("没有需要动态申请的权限")
            return
        }
        this.mCallback = callback
        this.mPermissions = permissions
        this.mReqPermissionTip = reqPermissionTip

        if (Build.VERSION.SDK_INT < 23) {
            PermLog.d("6.0 以下，无需申请权限，默认全部同意")
            dispatchResult(null)
            return
        }

        val needRequest = generateDeniedArray(permissions)
        // ① 目标权限均已授权，走成功的回调
        if (needRequest.isEmpty()) {
            dispatchResult(null)
            return
        }

        // ② 执行权限请求
        PermLog.d("需要申请的权限: $needRequest")
        realCall(needRequest)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun realCall(permissions: List<String>?) {
        if (permissions == null || permissions.isEmpty()) {
            dispatchResult(null)
            return
        }
        // ① 检索特殊权限
        val specialPermission: String? = permissions.firstOrNull {
            PermissionUtils.isSpecialPermission(it)
        }

        // ② 优先处理特殊权限
        specialPermission?.run {
            PermLog.d("含有特殊权限...")
            specialPermissionReq(this)
            return
        }

        // ③ 过滤掉特殊权限，处理普通权限
        val filter = permissions.filter {
            !it.equals(SYSTEM_ALERT_WINDOW, ignoreCase = true)
                    && !it.equals(WRITE_SETTINGS, ignoreCase = true)
        }
        if (filter.isEmpty()) {
            dispatchResult(null)
        } else {
            mPermissionReqLauncher?.launch(filter.toTypedArray())
        }
    }

    /**
     * 授权结果分发
     *
     * @param denied 未授权的权限
     */
    private fun dispatchResult(denied: List<String>?) {
        if (denied == null || denied.isEmpty()) {
            PermLog.d("所需权限均已全部授权-> 执行成功回调")
            mCallback?.onSucceed(mPermissions ?: mutableListOf())
        } else {
            PermLog.d("权限:${denied} 拒绝授权-> 执行失败回调")
            mCallback?.onFailed(denied)
        }
        release()
    }

    /**
     * 生成需要请求授权的权限数组，包含未请求过 和 之前请求请求失败的权限
     *
     * @param permissions   目标权限集
     * @return 需要请求授权的权限数组
     */
    private fun generateDeniedArray(permissions: List<String>?): List<String> {
        if (permissions.isNullOrEmpty()) return mutableListOf()

        val needReq = mutableListOf<String>()
        permissions.forEach {
            if (!PermissionUtils.hasPermission(requireContext(), it)) {
                needReq.add(it)
            }
        }
        return needReq
    }

    override fun resume() {
        //去系统设置中设置权限
        PermLog.d("用户选择再次授权->跳转权限设置页")
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", context?.packageName, null)
        intent.data = uri
        mSettingLauncher?.launch(intent)
    }

    override fun termination() {
        PermLog.d("用户终止了操作..")
        val needRequest = generateDeniedArray(mPermissions)
        dispatchResult(needRequest)
    }

    /**
     * 请求特殊权限
     *
     * @param specialPermission 特殊权限
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun specialPermissionReq(specialPermission: String) {
        when (specialPermission) {
            SYSTEM_ALERT_WINDOW -> {
                context?.run {
                    PermLog.d("含有特殊权限:android.permission.SYSTEM_ALERT_WINDOW，打开系统悬浮窗设置页面")
                    val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                    intent.data = Uri.parse("package:${this.packageName}")
                    mSystemAlertWindowLauncher?.launch(intent)
                }
            }
            WRITE_SETTINGS -> {
                context?.run {
                    PermLog.d("含有特殊权限:android.permission.WRITE_SETTINGS，打开允许修改系统设置页面")
                    val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                    intent.data = Uri.parse("package:${this.packageName}")
                    mSystemSettingLauncher?.launch(intent)
                }
            }
        }
    }

    /**
     * 特殊权限处理
     */
    private fun specialPermissionHandling(specialPermission: String) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // 6.0 以下直接授权成功，无须申请
            dispatchResult(null)
            return
        }
        var isAllow = true
        if (SYSTEM_ALERT_WINDOW.equals(specialPermission, ignoreCase = true)) {
            isAllow = Settings.canDrawOverlays(context)

        } else if (WRITE_SETTINGS.equals(specialPermission, ignoreCase = true)) {
            isAllow = Settings.System.canWrite(context)
        }
        val deniedArray = generateDeniedArray(mPermissions)
        if (isAllow) {
            // 当前权限已经同意，如果还有其他权限，继续执行
            realCall(deniedArray)
        } else {
            dispatchResult(deniedArray)
        }
    }

    private fun release() {
        PermLog.d("权限请求流程执行完毕 -> 重置数据，释放资源")
        mPermissions = null
        mCallback = null
        mReqPermissionTip = null
    }

    companion object {

        private const val SYSTEM_ALERT_WINDOW = Manifest.permission.SYSTEM_ALERT_WINDOW
        private const val WRITE_SETTINGS = Manifest.permission.WRITE_SETTINGS

        @JvmStatic
        fun getInstance(): PermissionFragment {
            return PermissionFragment()
        }
    }
}