package com.skx.common.permission

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.skx.common.base.BaseFragment

/**
 * 描述 : 权限
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/9/16 5:21 PM
 */
class PermissionFragment : BaseFragment(), PermissionNegotiate {

    /**
     * 申请的权限
     */
    private var mPermissions: Array<String>? = null
    private var mCallback: PermissionResultListener? = null
    private var mReqPermissionTip: ReqPermissionTip? = null

    private var mPermissionReqLauncher: ActivityResultLauncher<Array<String>>? = null
    private var mSettingLauncher: ActivityResultLauncher<Intent>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParams()
    }

    private fun initParams() {
        mPermissionReqLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result: Map<String?, Boolean?> ->
            val deniedList: MutableList<String> = mutableListOf()
            result.forEach {
                it.key?.run {
                    Log.d(DTAG, "授权结果: ${it.key}->${it.value}")
                    if (false == it.value) {
                        deniedList.add(this)
                    }
                }
            }
            // 1.拒绝的权限
            if (deniedList.isNotEmpty()) {
                // 1.1 外部接管
                if (mReqPermissionTip != null) {
                    mReqPermissionTip?.showReqPermissionsReason(this)
                    return@registerForActivityResult
                }
                // 1.2 外部不接管，走授权失败回调
                mCallback?.onFailed(deniedList.toTypedArray())
                release()
                return@registerForActivityResult
            }
            // 2.全部同意
            if (deniedList.isEmpty()) {
                mCallback?.onSucceed(mPermissions ?: arrayOf())
            }
            release()
        }
        // 权限设置
        mSettingLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            Log.e(DTAG, "从权限设置页返回")

        }
    }

    fun dispatchResult() {

    }

    /**
     * 判断第一次权限请求是否被拒绝了
     *
     * @param permissions
     * @return
     */
    fun shouldShowRationalePermissionsRationale(permissions: List<String?>): Boolean {
        for (permission in permissions) {
            val rationale = shouldShowRequestPermissionRationale(permission!!)
            if (rationale) return true
        }
        return false
    }

    fun reqPermissions(
        permissions: Array<String>?,
        callback: PermissionResultListener?,
        reqPermissionTip: ReqPermissionTip?
    ) {
        if (permissions == null || permissions.isEmpty()) {
            Log.d(DTAG, "没有需要动态申请的权限")
            return
        }
        if (Build.VERSION.SDK_INT < 23) {
            Log.d(DTAG, "6.0 以下，无需申请权限")
            return
        }

        val needRequest = mutableListOf<String>()
        permissions.forEach {
            if (!checkPermission(it)) {
                needRequest.add(it)
            }
        }

        if (needRequest.size <= 0) {
            Log.d(DTAG, "目标权限均已全部授权过")
            callback?.onSucceed(mPermissions ?: arrayOf())
            return
        }
        this.mCallback = callback
        this.mPermissions = permissions
        this.mReqPermissionTip = reqPermissionTip
        val needReq = needRequest.toTypedArray()
        Log.d(DTAG, "需要申请的权限: $needReq")
        mPermissionReqLauncher?.launch(needReq)
    }

    /**
     * 检查是否该权限是否已经授权
     *
     * @param permission 目标权限
     */
    private fun checkPermission(permission: String): Boolean {
        val result = ContextCompat.checkSelfPermission(
            mContext!!,
            permission
        ) == PackageManager.PERMISSION_GRANTED
        Log.d(DTAG, "checkPermission: ${permission}->${result}")
        return result
    }

    override fun onDestroy() {
        super.onDestroy()
        mCallback = null
    }

    override fun resume() {
        //去系统设置中设置权限
        Log.e(DTAG, "用户选择再次授权->跳转权限设置页")
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", context?.packageName, null)
        intent.data = uri
        mSettingLauncher?.launch(intent)
    }

    override fun termination() {
        Log.e(DTAG, "用户终止了操作..")

    }

    fun release() {
        Log.e(DTAG, "重置数据，释放资源")
        mPermissions = null
        mCallback = null
        mReqPermissionTip = null
    }

    companion object {

        private const val DTAG = "PermissionController"

        @JvmStatic
        fun getInstance(): PermissionFragment {
            return PermissionFragment()
        }
    }
}