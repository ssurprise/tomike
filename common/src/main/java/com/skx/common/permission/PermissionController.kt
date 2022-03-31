package com.skx.common.permission

import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * 描述 : 权限控制类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/9/16 5:21 PM
 */
class PermissionController(private val params: PermissionParams) {

    companion object {
        private const val KEY_PERMISSION_FRAGMENT = "tomike_permission_fragment"
    }

    fun requestPermission() {
        if (params.mPermissions == null || params.mPermissions?.isEmpty() == true) {
            PermLog.d("当前没有需要动态申请的权限")
            params.mError?.onErrorEvent(
                PermissionError.ERROR_CODE_PERMISSION_EMPTY,
                "当前没有需要动态申请的权限"
            )
            return
        }

        if (params.mContext == null && params.mFragmentManager == null) {
            PermLog.d("缺少上下文")
            params.mError?.onErrorEvent(
                PermissionError.ERROR_CODE_CONTEXT_NOT_FIND,
                "缺少上下文"
            )
            return
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // 6.0下安装时默认同意全部权限
            PermLog.d("6.0以下系统，无需动态申请权限")
            params.mCallback?.onSucceed(params.mPermissions!!)
            return
        }
        params.mFragmentManager?.run {
            var fragment = this.findFragmentByTag(KEY_PERMISSION_FRAGMENT)
            if (fragment == null) {
                fragment = PermissionFragment.getInstance()
                this.beginTransaction()
                    .add(fragment, KEY_PERMISSION_FRAGMENT)
                    .commitNow()
            }
            if (fragment is PermissionFragment) {
                fragment.reqPermissions(
                    params.mPermissions,
                    params.mCallback,
                    params.mReqPermissionTip
                )
            }
            return
        }
        params.mContext?.run {
            PermLog.d("不支持的上下文")
            params.mError?.onErrorEvent(
                PermissionError.ERROR_CODE_UNSUPPORT_CONTEXT,
                "不支持的上下文"
            )
        }
    }

    /**
     * 描述 : 权限控制类构造者，用于发起权限请求
     * 作者 : shiguotao
     * 版本 : V1
     * 创建时间 : 2020/9/16 5:21 PM
     */
    class Builder private constructor() {

        private val params: PermissionParams = PermissionParams()

        constructor(context: Context?) : this() {
            params.mContext = context
            val activity = scanForActivity(context)
            params.mFragmentManager = activity?.supportFragmentManager
        }

        private fun scanForActivity(cont: Context?): FragmentActivity? {
            return when (cont) {
                null -> null
                is FragmentActivity -> cont
                is ContextWrapper -> {
                    scanForActivity(cont.baseContext)
                }
                else -> null
            }
        }

        constructor(fragmentActivity: FragmentActivity?) : this() {
            params.mContext = fragmentActivity
            params.mFragmentManager = fragmentActivity?.supportFragmentManager
        }

        constructor(fragment: Fragment?) : this() {
            params.mContext = fragment?.context
            params.mFragmentManager = fragment?.childFragmentManager
        }

        fun permissions(permissions: Array<String>): Builder {
            params.mPermissions = permissions
            return this
        }

        fun permissions(permissions: List<String>): Builder {
            params.mPermissions = permissions.toTypedArray()
            return this
        }

        fun callback(callback: PermissionResultListener): Builder {
            params.mCallback = callback
            return this
        }

        fun associateDefaultTip(): Builder {
            params.mReqPermissionTip = DefaultReqPermissionTip(params.mContext)
            return this
        }

        fun associateCustomTip(reqPermissionTip: ReqPermissionTip): Builder {
            params.mReqPermissionTip = reqPermissionTip
            return this
        }

        fun error(error: PermissionError): Builder {
            params.mError = error
            return this
        }

        fun create(): PermissionController {
            return PermissionController(params)
        }

        fun request() {
            val controller = PermissionController(params)
            controller.requestPermission()
        }
    }
}