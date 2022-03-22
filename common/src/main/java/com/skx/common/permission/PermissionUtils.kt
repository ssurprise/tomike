package com.skx.common.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
import androidx.annotation.RequiresApi
import androidx.core.app.AppOpsManagerCompat
import androidx.core.content.ContextCompat
import java.util.*

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
    private fun hasPermission(context: Context, permissions: Array<String>): Boolean {
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
            if (AppOpsManagerCompat.MODE_IGNORED == AppOpsManagerCompat.noteProxyOp(
                    context,
                    op!!,
                    context.packageName
                )
            ) {
                return false
            }
            if (ContextCompat.checkSelfPermission(
                    context,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun getDeniedPermissions(context: Context?, permissions: List<String>): List<String>? {
        val deniedList: MutableList<String> = ArrayList(1)
        for (permission in permissions) {
            if (isSpecialPermission(permission)) {
                if (Manifest.permission.SYSTEM_ALERT_WINDOW.equals(permission, ignoreCase = true)
                    && !Settings.canDrawOverlays(context)
                ) {
                    deniedList.add(permission) // 特殊权限
                }
                if (Manifest.permission.WRITE_SETTINGS.equals(permission, ignoreCase = true)
                    && !Settings.System.canWrite(context)
                ) {
                    deniedList.add(permission) // 特殊权限
                }
            } else if (ContextCompat.checkSelfPermission(
                    context!!,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                deniedList.add(permission)
            }
        }
        return deniedList
    }

    /**
     * 判断是否为特殊权限
     *
     * @param permission
     * @return
     */
    private fun isSpecialPermission(permission: String): Boolean {
        return (Manifest.permission.SYSTEM_ALERT_WINDOW.equals(permission, ignoreCase = true)
                || Manifest.permission.WRITE_SETTINGS.equals(permission, ignoreCase = true))
    }

    /**
     * 释放资源
     */
    fun release() {

    }
}