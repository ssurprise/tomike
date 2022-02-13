package com.skx.common.permission;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 静态工具方法
 * @Author: fengzeyuan
 * @Date: 17/6/23 下午7:33
 * @Version: 1.0
 */
public class XZPermissionUtils {

    /**
     * 相册权限
     * 特殊说明：相册其实是读取本地的照片，应当是读写权限（READ_EXTERNAL_STORAGE），但是出于"网安"要求，提示需明确
     * 故，这里声明了一个自定义的权限来映射读权限，仅仅是做显示用，真正申请的还是读权限。
     */
    private static final String XZ_PERMISSION_PHOTO_ALBUM = "xiaozhu.permission.PHOTO_ALBUM";

    private static final Map<String, String> MAP_LABEL_STR = new HashMap<String, String>() {
        {
            if (Build.VERSION.SDK_INT >= 16) {
                put(Manifest.permission.READ_EXTERNAL_STORAGE, "存储");
            }
            put(Manifest.permission.WRITE_EXTERNAL_STORAGE, "存储");
            put(Manifest.permission.ACCESS_COARSE_LOCATION, "位置信息");
            put(Manifest.permission.ACCESS_FINE_LOCATION, "位置信息");
            put(Manifest.permission.READ_PHONE_STATE, "电话");
            put(Manifest.permission.CAMERA, "相机");
            put(XZ_PERMISSION_PHOTO_ALBUM, "相册");
        }
    };
    private static final Map<String, String> MAP_LABEL_DESC_STR = new HashMap<String, String>() {
        {
            if (Build.VERSION.SDK_INT >= 16) {
                put(Manifest.permission.READ_EXTERNAL_STORAGE, "（开启后数据可在本地缓存，便于您更便捷的使用和更新小猪APP）");
            }
            put(Manifest.permission.WRITE_EXTERNAL_STORAGE, "（开启后数据可在本地缓存，便于您更便捷的使用和更新小猪APP）");
            put(Manifest.permission.ACCESS_COARSE_LOCATION, "（开启后可获取您的位置，便于您使用搜索功能寻找附近的房屋）");
            put(Manifest.permission.ACCESS_FINE_LOCATION, "（开启后可获取您的位置，便于您使用搜索功能寻找附近的房屋）");
            put(Manifest.permission.READ_PHONE_STATE, "（开启后获取唯一设备信息，用于用户识别统计访问信息）");
            put(Manifest.permission.CAMERA, "（开启后可使用您的相机进行拍摄，用于您使用头像上传、实名认证、上传房源照片等功能）");
            put(XZ_PERMISSION_PHOTO_ALBUM, "（开启后可访问您的相册，用于您使用头像上传、实名认证、上传房源照片等功能）");
        }
    };
    private static Set<String> permissions;

    private static final String[] GROUP_STORAGE = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    public static void checkPermission(Context cx, String[] permission, PermissionResultListener listener) {
        checkPermission(cx, true, permission, listener);
    }

    public static void checkPermission(Context cx, boolean showTipAtFirst, String[] permission, PermissionResultListener listener) {
        XZPermissionUtils
                .with(cx, showTipAtFirst)
                .permission(permission)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, DialogHandler requestRationaleHandler) {
                        requestRationaleHandler.showDefaultDialog("正在获取权限", "该功能需获取新的权限，否则无法正常使用");
                    }

                    @Override
                    public void showSettingPermissionRationale(int requestCode, DialogHandler settingRationaleHandler) {
                        settingRationaleHandler.showDefaultDialog("权限获取失败", "权限请求失败，无法正常使用该功能，是否去“设置“中开启权限？");
                    }
                })
                .callback(listener)
                .request();
    }

    /**
     * 获取权限请求对象
     *
     * @param cx
     * @return
     */
    public static XZPermissionRequest with(@NonNull Context cx) {
        return with(cx, true);
    }


    /**
     * 获取权限请求对象
     *
     * @param cx
     * @param showTipAtFirst 第一次是否展示提示
     * @return
     */
    public static XZPermissionRequest with(@NonNull Context cx, boolean showTipAtFirst) {
        if (!(cx instanceof Application)) {
            cx = cx.getApplicationContext();
        }
        return new XZPermissionRequest(cx, showTipAtFirst);
    }

    /**
     * Check if the calling context has a set of permissions.
     *
     * @param context     {@link Context}.
     * @param permissions one or more permissions.
     * @return true, other wise is false.
     */
    public static boolean hasPermission(@NonNull Context context, @NonNull String... permissions) {
        return hasPermission(context, Arrays.asList(permissions));
    }

    /**
     * Check if the calling context has a set of permissions.
     *
     * @param context     {@link Context}.
     * @param permissions one or more permissions.
     * @return true, other wise is false.
     */
    public static boolean hasPermission(@NonNull Context context, @NonNull List<String> permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;
        for (String permission : permissions) {

            if (isSpecialPermission(permission)) {
                // 特殊权限检查
                if (!hasSpecialPermission(context, permission))
                    return false;
            } else {
                // 危险权限检查
                String op = AppOpsManagerCompat.permissionToOp(permission);
                if (TextUtils.isEmpty(op))
                    continue;
                int result = AppOpsManagerCompat.noteProxyOp(context, op, context.getPackageName());
                if (result == AppOpsManagerCompat.MODE_IGNORED)
                    return false;
                result = ContextCompat.checkSelfPermission(context, permission);
                if (result != PackageManager.PERMISSION_GRANTED)
                    return false;
            }
        }
        return true;
    }

    /**
     * 判断是否为特殊权限
     *
     * @param permission
     * @return
     */
    private static boolean isSpecialPermission(String permission) {
        return Manifest.permission.SYSTEM_ALERT_WINDOW.equalsIgnoreCase(permission)
                || Manifest.permission.WRITE_SETTINGS.equalsIgnoreCase(permission);
    }

    /**
     * 获取权限的名字
     *
     * @param context
     * @param permission
     * @return
     */
    public static String getPermissionLabelStr(Context context, String permission) {
        String labelStr = "";
        PackageManager packageManager = context.getPackageManager();
        try {
            PermissionInfo permissionInfo = packageManager.getPermissionInfo(permission, 0);
            return permissionInfo.loadDescription(packageManager).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return labelStr;
    }

    /**
     * 获取权限组的名字
     *
     * @param context
     * @param permission
     * @return
     */
    public static String getPermissionGroupLabelStr(Context context, String permission) {
        String labelStr = "";
        PackageManager packageManager = context.getPackageManager();
        try {
            PermissionInfo permissionInfo = packageManager.getPermissionInfo(permission, 0);
            PermissionGroupInfo permissionGroupInfo = packageManager.getPermissionGroupInfo(permissionInfo.group, 0);
            labelStr = permissionGroupInfo.loadLabel(packageManager).toString();
            if (TextUtils.isEmpty(labelStr) || TextUtils.equals("android.permission-group.UNDEFINED", labelStr)) {
                if (MAP_LABEL_STR.containsKey(permission)) {
                    labelStr = MAP_LABEL_STR.get(permission);
                }
            }
            return labelStr;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return labelStr;
    }

    /**
     * 获取权限组的名字
     *
     * @param context
     * @param permissions
     * @return
     */
    public static Set<String> getPermissionGroupLabels(Context context, List<String> permissions) {
        HashSet<String> groupLabel = new HashSet<>();
        for (String permission : permissions) {
            String label = getPermissionGroupLabelStr(context, permission);
            if (TextUtils.isEmpty(label)) {
                continue;
            }
            // 查询权限使用描述
            String labelDesc = MAP_LABEL_DESC_STR.containsKey(permission) ? MAP_LABEL_DESC_STR.get(permission) : "";
            groupLabel.add(label + labelDesc);
        }
        return groupLabel;
    }

    /**
     * 获取还未获取成功的权限
     *
     * @param context
     * @param permissions
     * @return
     */
    @TargetApi(Build.VERSION_CODES.M)
    static List<String> getDeniedPermissions(Context context, @NonNull List<String> permissions) {
        List<String> deniedList = new ArrayList<>(1);
        for (String permission : permissions) {
            if (isSpecialPermission(permission)) {
                if (Manifest.permission.SYSTEM_ALERT_WINDOW.equalsIgnoreCase(permission) && !Settings.canDrawOverlays(context)) {
                    deniedList.add(permission);// 特殊权限
                }
                if (Manifest.permission.WRITE_SETTINGS.equalsIgnoreCase(permission) && !Settings.System.canWrite(context)) {
                    deniedList.add(permission);// 特殊权限
                }
            } else if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                deniedList.add(permission);
            }
        }
        return deniedList;
    }

    /**
     * 检测清单文件中是否注册了该权限
     *
     * @param context
     * @param permissions
     * @return
     */
    static boolean isPermissionInManifest(@NonNull Context context, @NonNull String[] permissions) {
        boolean result = true;
        for (String permission : permissions) {
            result &= getAllPermission(context).contains(permission);
        }
        return result;
    }

    /**
     * 获取所有在清单文件上注册的权限
     *
     * @param cx
     * @return
     */
    private static Set<String> getAllPermission(Context cx) {
        if (permissions == null) {
            permissions = new HashSet<>();
            PackageInfo packageInfo;
            try {
                PackageManager packageManager = cx.getPackageManager();
                packageInfo = packageManager.getPackageInfo(cx.getPackageName(), PackageManager.GET_PERMISSIONS);
                if (packageInfo.requestedPermissions != null) {
                    permissions.addAll(Arrays.asList(packageInfo.requestedPermissions));
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return permissions;
    }

    /**
     * 是否有特殊权限
     *
     * @param context
     * @param permissions
     * @return
     */
    private static boolean hasSpecialPermission(@NonNull Context context, @NonNull String... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;
        for (String permission : permissions) {
            if (isSpecialPermission(permission)) {
                if (Manifest.permission.SYSTEM_ALERT_WINDOW.equalsIgnoreCase(permission) && !Settings.canDrawOverlays(context)) {
                    return false;// 特殊权限
                }
                if (Manifest.permission.WRITE_SETTINGS.equalsIgnoreCase(permission) && !Settings.System.canWrite(context)) {
                    return false;// 特殊权限
                }
            }
        }
        return true;
    }

    // 存储权限
    public static String[] getGroupStorage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
        }
        return new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    }

}
