package com.skx.common.permission

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
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
class PermissionFragment : BaseFragment() {

    /**
     * 申请的权限
     */
    private var mPermissions: Array<String>? = null
    private var mActivityResultLauncher: ActivityResultLauncher<Array<String>>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParams()
    }

    private fun initParams() {
        mActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result: Map<String?, Boolean?> ->
            val deniedList: MutableList<String> = mutableListOf()
            result.forEach {
                it.key?.run {
                    Log.d(TAG, "授权结果: ${it.key}->${it.value}")
                    if (false == it.value) {
                        deniedList.add(this)
                    }
                }
            }
            // 1.拒绝的权限
            if (deniedList.isNotEmpty()) {
                PermissionUtils.mCallback?.onFailed(deniedList.toTypedArray())
                return@registerForActivityResult
            }
            // 2.全部同意
            if (deniedList.isEmpty()) {
                PermissionUtils.mCallback?.onSucceed(mPermissions ?: arrayOf())
            }
        }
    }


    fun reqPermissions(permissions: Array<String>?) {
        if (permissions == null || permissions.isEmpty()) {
            Log.d(TAG, "没有需要动态申请的权限")
            return
        }
        if (Build.VERSION.SDK_INT < 23) {
            Log.d(TAG, "6.0 以下，无需申请权限")
            return
        }

        val needRequest = mutableListOf<String>()
        permissions.forEach {
            if (!checkPermission(it)) {
                needRequest.add(it)
            }
        }

        if (needRequest.size <= 0) {
            Log.d(TAG, "目标权限均已全部授权过")
            PermissionUtils.mCallback?.onSucceed(mPermissions ?: arrayOf())
            return
        }

        mPermissions = permissions
        val needReq = needRequest.toTypedArray()
        Log.d(TAG, "需要申请的权限: $needReq")
        mActivityResultLauncher?.launch(needReq)
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
        Log.d(TAG, "checkPermission: ${permission}->${result}")
        return result
    }

    companion object {
        private const val TAG = "PermissionUtils"

        @JvmStatic
        fun getInstance(): PermissionFragment {
            return PermissionFragment()
        }
    }
}