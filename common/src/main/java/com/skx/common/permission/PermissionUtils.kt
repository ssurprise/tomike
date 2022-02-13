package com.skx.common.permission

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.text.TextUtils
import android.util.Log
import androidx.core.app.AppOpsManagerCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

/**
 * 描述 : 权限工具类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/9/16 5:19 PM
 */
object PermissionUtils {

    private const val TAG = "PermissionUtils"

    private fun requestPermission(
        fragmentManager: FragmentManager,
        permission: Array<String>?,
        listener: PermissionResultListener?
    ) {
        if (permission == null || permission.isEmpty()) {
            Log.d(TAG, "当前没有需要动态申请的权限")
            return
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // 6.0下安装时默认同意全部权限
//            callBackResult()
            Log.d(TAG, "6.0以下系统，无需动态申请权限")
        }

        var fragment = fragmentManager.findFragmentByTag("permission_fragment")
        val isNewInstance = fragment == null
        if (isNewInstance) {
            fragment = PermissionFragment.getInstance(permission)
            fragmentManager
                .beginTransaction()
                .add(fragment, "permission_fragment")
                .commitNow()
        }
    }

    fun requestPermission(
        fragment: Fragment,
        permission: Array<String>?,
        listener: PermissionResultListener?
    ) {
        requestPermission(fragment.parentFragmentManager, permission, listener)
    }

    /**
     * 检查权限
     *
     * @param activity   上下文
     * @param permission 申请的权限
     * @param listener   授权结果回调
     */
    fun requestPermission(
        activity: FragmentActivity,
        permission: Array<String>?,
        listener: PermissionResultListener?
    ) {
        requestPermission(activity.supportFragmentManager, permission, listener)
    }

    /**
     * 检测清单文件中是否注册了该权限
     *
     * @param context
     * @param permissions
     * @return
     */
//    fun isPermissionInManifest(context: Context, permissions: Array<String?>): Boolean {
//        var result = true
//        for (permission in permissions) {
//            result = result and XZPermissionUtils.getAllPermission(context).contains(permission)
//        }
//        return result
//    }

    /**
     * 检查目标权限是否已被授权
     *
     * @param context     上下文
     * @param permissions 需要检查的权限
     * @return true：全部均已授权
     */
    fun hasPermission(context: Context, permissions: List<String>): Boolean {
        return hasPermission(context, permissions.toTypedArray())
    }

    /**
     * 检查目标权限是否已被授权
     *
     * @param context     上下文
     * @param permissions 需要检查的权限
     * @return true：全部均已授权
     */
    fun hasPermission(context: Context, permissions: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) { // 6.0 以下的系统
            return true
        }
        for (permission in permissions) {
            /*
            小米（6.0以上）
            定位权限，用户虽然拒绝了权限，但是代码回调中返回的却是同意。
            解决方法：在权限回调中，运用系统层，判断是否真的获取权限
             */
            val op = AppOpsManagerCompat.permissionToOp(permission)
            if (TextUtils.isEmpty(op)) {
                continue
            }
            var result = AppOpsManagerCompat.noteProxyOp(context, op!!, context.packageName)
            if (result == AppOpsManagerCompat.MODE_IGNORED) return false
            result = ContextCompat.checkSelfPermission(context, permission)
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    /*
     1.检查是否有某个权限

     2.申请权限
        2.1 链式
        2.2 聚合式

     基本诉求：
     1.请求单个权限
     2.请求多个权限 -> 结果一起处理 ； 可中断，某个权限拒绝后不在请求下个权限。


     实现方案：
     1.类RxJava 式的函数式编程。

     */
    class Builder {
        fun with(context: Context?): Builder {
            return this
        }

        fun with(fragmentActivity: FragmentActivity?): Builder {
            return this
        }

        fun with(fragment: Fragment?): Builder {
            return this
        }

        private fun requestPermission(vararg permissions: String): Builder {
            return this
        }
    }
}