package com.skx.common.permission;

import androidx.annotation.NonNull;


/**
 * 描述 : 权限授权结果回调
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/9/16 5:32 PM
 */
public interface PermissionResultListener {

    /**
     * 申请权限全部成功时回调
     *
     * @param grantPermissions 请求的权限
     */
    void onSucceed(@NonNull String[] grantPermissions);

    /**
     * 申请权限失败回调
     *
     * @param deniedPermissions 请求失败的权限
     */
    void onFailed(@NonNull String[] deniedPermissions);

}