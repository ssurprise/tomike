package com.skx.common.utils;


import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import androidx.annotation.RequiresPermission;

/**
 * 描述 : 设备信息工具类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/6/14 12:04 上午
 */
public class DevicesUtils {


    /**
     * 获取屏幕宽度
     *
     * @param context 上下文
     * @return
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param context 上下文
     * @return
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取dpi
     *
     * @param context 上下文
     * @return
     */
    public static int getDensityDpi(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    /**
     * 获取设备的sn
     *
     * @return SN
     */
    @RequiresPermission("android.permission.READ_PRIVILEGED_PHONE_STATE")
    public static String getSerialNo() {
        // 注意：由于Android 10 已无法正常获取SN / IMEI，需要系统签名，否则会抛出SecurityException错误。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Build.getSerial();
        }
        /* 还有一种方法，无需申请权限，通过反射的方式获取
        String serial = null;
        try {
            Class<?> c =Class.forName("android.os.SystemProperties");
            Method get =c.getMethod("get", String.class);
            serial = (String)get.invoke(c, "ro.serialno");
        } catch (Exception e) {
            e.printStackTrace();
        }
         */
        return Build.SERIAL;
    }

    @RequiresPermission("android.permission.READ_PRIVILEGED_PHONE_STATE")
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * 获取设备的imei
     * 注意：Android Q(10) 之后禁止使用
     *
     * @param context 上下文
     * @return
     */
    @RequiresPermission("android.permission.READ_PRIVILEGED_PHONE_STATE")
    public static String getImei(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {// Android 8
            return tm.getImei();
        }
        return "";
    }

   /**
     * 获取设备的meid
     * 注意：Android Q(10) 之后禁止使用
     *
     * @param context 上下文
     * @return
     */
    @RequiresPermission("android.permission.READ_PRIVILEGED_PHONE_STATE")
    public static String getMeid(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {// Android 8
            return tm.getMeid();
        }
        return "";
    }

    /**
     * 获取设备的 ANDROID_ID
     * ANDROID_ID:是设备首次启动时由系统随机生成的一串64位的十六进制数字
     *
     * @param context 上下文
     * @return
     */
    public static String getAndroidId(Context context) {
        return Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * 获取设备制造商
     *
     * @return
     */
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取设备品牌
     *
     * @return
     */
    public static String getBrand() {
        return Build.BRAND;
    }

    /**
     * 获取设备型号
     *
     * @return
     */
    public static String getModel() {
        return Build.MODEL;
    }
}
