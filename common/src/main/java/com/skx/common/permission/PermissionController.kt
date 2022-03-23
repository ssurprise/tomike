package com.skx.common.permission

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity


class PermissionController(private val params: PermissionParams) {

    companion object {
        private const val TAG = "PermissionController"
        private const val KEY_PERMISSION_FRAGMENT = "tomike_permission_fragment"
    }

    fun requestPermission() {
        if (params.mPermissions == null || params.mPermissions?.isEmpty() == true) {
            Log.d(TAG, "当前没有需要动态申请的权限")
            return
        }

        if (params.mContext == null && params.mFragmentManager == null) {
            Log.d(TAG, "缺少上下文")
            return
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // 6.0下安装时默认同意全部权限
            Log.d(TAG, "6.0以下系统，无需动态申请权限")
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
            // todo
        }
    }

    class Builder private constructor() {

        private val params: PermissionParams = PermissionParams()

        constructor(context: Context?) : this() {
            params.mContext = context
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

        fun associateDefaultTip():Builder{
            params.mReqPermissionTip = DefaultReqPermissionTip(params.mContext)
            return this
        }

        fun associateCustomTip(reqPermissionTip: ReqPermissionTip): Builder {
            params.mReqPermissionTip = reqPermissionTip
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