package com.skx.common.permission

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.skx.common.base.BaseFragment

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
            // 注：这里之所有没有直接使用回调参数中的result 进行判断是因为有些特殊权限不会通过这里获取授权结果。
//            val deniedList: MutableList<String> = mutableListOf()
//            result.forEach {
//                it.key?.run {
//                    Log.d(DTAG, "授权结果: ${it.key}->${it.value}")
//                    if (false == it.value) {
//                        deniedList.add(this)
//                    }
//                }
//            }
            val deniedArray = generateDeniedArray(mPermissions)
            // 1.全部同意
            if (deniedArray.isNullOrEmpty()) {
                dispatchResult(null)
                return@registerForActivityResult
            }
            // 2.拒绝的权限
            // 2.1 外部接管
            if (mReqPermissionTip != null) {
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
            Log.e(DTAG, "从权限设置页返回 resultCode:${it.resultCode}")
            val needRequest = generateDeniedArray(mPermissions)
            dispatchResult(needRequest)
        }
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

        val needRequest = generateDeniedArray(permissions)
        if (needRequest.isEmpty()) {
            Log.d(DTAG, "目标权限均已全部授权过")
            callback?.onSucceed(permissions)
            return
        }
        this.mCallback = callback
        this.mPermissions = permissions
        this.mReqPermissionTip = reqPermissionTip
        Log.d(DTAG, "需要申请的权限: $needRequest")
        mPermissionReqLauncher?.launch(needRequest)
    }

    /**
     * 授权结果分发
     *
     * @param denied 未授权的权限
     */
    private fun dispatchResult(denied: Array<String>?) {
        if (denied == null || denied.isEmpty()) {
            Log.d(DTAG, "所需权限均已全部授权-> 执行成功回调")
            mCallback?.onSucceed(mPermissions ?: arrayOf())
        } else {
            Log.d(DTAG, "权限:${denied} 拒绝授权-> 执行失败回调")
            mCallback?.onFailed(denied)
        }
        release()
    }

    /**
     * 生成需要请求授权的权限数组
     *
     * @param permissions   目标权限集
     * @return 需要请求授权的权限数组
     */
    private fun generateDeniedArray(permissions: Array<String>?): Array<String> {
        if (permissions == null || permissions.isEmpty()) return arrayOf()

        val needReq = mutableListOf<String>()
        permissions.forEach {
            if (!PermissionUtils.hasPermission(requireContext(), it)) {
                needReq.add(it)
            }
        }
        return needReq.toTypedArray()
    }

    override fun resume() {
        //去系统设置中设置权限
        Log.d(DTAG, "用户选择再次授权->跳转权限设置页")
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", context?.packageName, null)
        intent.data = uri
        mSettingLauncher?.launch(intent)
    }

    override fun termination() {
        Log.d(DTAG, "用户终止了操作..")
    }

    fun release() {
        Log.d(DTAG, "权限请求流程执行完毕 -> 重置数据，释放资源")
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