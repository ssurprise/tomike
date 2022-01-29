package com.skx.common.permission;


import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import java.util.Arrays;
import java.util.List;

/**
 * 描述 : 权限工具类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/9/16 5:19 PM
 */
public class PermissionUtils {


    /**
     * 检查权限
     *
     * @param cx         上下文
     * @param permission 申请的权限
     * @param listener   授权结果回调
     */
    public static void checkPermission(FragmentActivity cx, String[] permission, PermissionResultListener listener) {
        PermissionFragment permissionFragment = PermissionFragment.getInstance(permission);
        cx.getSupportFragmentManager()
                .beginTransaction()
                .add(permissionFragment, "aaa")
                .commitNow();
    }


    /**
     * 检查目标权限是否已被授权
     *
     * @param context     上下文
     * @param permissions 需要检查的权限
     * @return true：全部均已授权
     */
    public static boolean hasPermission(@NonNull Context context, @NonNull String... permissions) {
        return hasPermission(context, Arrays.asList(permissions));
    }

    /**
     * 检查目标权限是否已被授权
     *
     * @param context     上下文
     * @param permissions 需要检查的权限
     * @return true：全部均已授权
     */
    public static boolean hasPermission(@NonNull Context context, @NonNull List<String> permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {// 6.0 以下的系统
            return true;
        }
        for (String permission : permissions) {
            /*
            小米（6.0以上）
            定位权限，用户虽然拒绝了权限，但是代码回调中返回的却是同意。
            解决方法：在权限回调中，运用系统层，判断是否真的获取权限
             */
            String op = AppOpsManagerCompat.permissionToOp(permission);
            if (TextUtils.isEmpty(op)) {
                continue;
            }
            int result = AppOpsManagerCompat.noteProxyOp(context, op, context.getPackageName());
            if (result == AppOpsManagerCompat.MODE_IGNORED)
                return false;

            result = ContextCompat.checkSelfPermission(context, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }

        }
        return true;
    }

}
