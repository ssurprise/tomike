package com.skx.common.utils;

import android.content.Context;
import android.hardware.Camera;
import android.location.LocationManager;

/**
 * Created by shiguotao on 2016/6/17.
 * <p>
 * 权限许可工具类
 */
public class PermissionCheck {

    /**
     * 判断摄像头是否可用
     * 主要针对6.0 之前的版本，现在主要是依靠try...catch... 报错信息，感觉不太好，以后有更好的方法的话可适当替换
     *
     * @return
     */
    public static boolean isCameraCanUse() {
        boolean canUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
            // setParameters 是针对魅族MX5 做的。MX5 通过Camera.open() 拿到的Camera 对象不为null
            Camera.Parameters mParameters = mCamera.getParameters();
            mCamera.setParameters(mParameters);
        } catch (Exception e) {
            canUse = false;
        }
        if (mCamera != null) {
            mCamera.release();
        }
        return canUse;
    }

    /**
     * 定位服务是否可用（目前是有GPS 和 网络定位，其他的像 wifi、蓝牙、mac地址定位的并没有）
     */
    public static boolean locationServiceOpen(Context context) {
        LocationManager alm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (alm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //GPS已开启
            return true;
        } else if (alm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            //网络定位开启
            return true;
        } else {
            return false;
        }
    }
}
