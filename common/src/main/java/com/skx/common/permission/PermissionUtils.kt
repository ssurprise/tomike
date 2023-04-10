package com.skx.common.permission

import android.Manifest
import android.app.AppOpsManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Process
import android.provider.Settings
import android.text.TextUtils
import androidx.core.app.AppOpsManagerCompat
import androidx.core.content.ContextCompat

/**
 * 描述 : 权限工具类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/9/16 5:19 PM
 */
object PermissionUtils {

    /**
     * 检查目标权限是否已被授权
     *
     * @param context       上下文
     * @param permission    目标权限检查的权限
     * @return true:目标权限已授权，false:目标权限未授权
     */
    fun hasPermission(context: Context, permission: String): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) { // 6.0 以下的系统
            return true
        }

        // 特殊权限 - 浮悬窗
        if (Manifest.permission.SYSTEM_ALERT_WINDOW.equals(permission, ignoreCase = true)) {
            return Settings.canDrawOverlays(context)
        }

        // 特殊权限-系统设置
        if (Manifest.permission.WRITE_SETTINGS.equals(permission, ignoreCase = true)) {
            return Settings.System.canWrite(context)
        }

        // 特殊权限-使用情况访问权限
        if (Manifest.permission.PACKAGE_USAGE_STATS.equals(permission, ignoreCase = true)) {
            val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
            val mode = appOps.checkOpNoThrow("android:get_usage_stats",
                    Process.myUid(), context.packageName)
            return mode == AppOpsManager.MODE_ALLOWED
        }

        // 常规权限检查，但是有漏洞。比如在小米（6.0以上） 定位权限，用户虽然拒绝了权限，但是代码回调中返回的却是同意。
        // 解决方法：在权限回调中，运用系统层，判断是否真的获取权限
        if (ContextCompat.checkSelfPermission(
                        context,
                        permission
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }

        // 系统层权限检查
        val op = AppOpsManagerCompat.permissionToOp(permission)
        if (!TextUtils.isEmpty(op)
                && AppOpsManagerCompat.MODE_IGNORED == AppOpsManagerCompat.noteProxyOp(
                        context,
                        op!!,
                        context.packageName
                )
        ) {
            return false
        }
        return true
    }

    /**
     * 检查目标权限是否已被授权
     *
     * @param context     上下文
     * @param permissions 需要检查的权限
     * @return true：全部均已授权
     */
    private fun hasPermission(context: Context, permissions: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) { // 6.0 以下的系统
            return true
        }
        for (permission in permissions) {
            val result = hasPermission(context, permission)
            if (!result) {
                return false
            }
        }
        return true
    }

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
     * 判断是否为特殊权限
     *
     * @param permission
     * @return
     */
    fun isSpecialPermission(permission: String): Boolean {
        return Manifest.permission.SYSTEM_ALERT_WINDOW.equals(permission, ignoreCase = true)
                || Manifest.permission.WRITE_SETTINGS.equals(permission, ignoreCase = true)
                || Manifest.permission.PACKAGE_USAGE_STATS.equals(permission, ignoreCase = true)
    }

}