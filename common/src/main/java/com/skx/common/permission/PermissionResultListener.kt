package com.skx.common.permission

/**
 * 描述 : 权限授权结果回调
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/9/16 5:32 PM
 */
interface PermissionResultListener {
    /**
     * 申请权限全部成功时回调
     *
     * @param grantPermissions 请求的权限
     */
    fun onSucceed(grantPermissions: Array<String>?)

    /**
     * 申请权限失败回调
     *
     * @param deniedPermissions 请求失败的权限
     */
    fun onFailed(deniedPermissions: Array<String>?)
}