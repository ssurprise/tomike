package com.skx.common.utils

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.KeyCharacterMap
import android.view.KeyEvent
import android.view.ViewConfiguration
import android.view.WindowManager


/**
 * 获取屏幕宽度
 */
fun getScreenWidth(context: Context): Int {
    return context.resources.displayMetrics.widthPixels
}

/**
 * 获得屏幕高度
 *
 * @param context 上下文
 * @return 高度
 */
fun getScreenHeight(context: Context): Int {
    return context.resources.displayMetrics.heightPixels
}

/**
 * 通过属性获得状态栏高度
 *
 * @param context 上下文
 * @return 状态栏高度
 */
fun getStatusBarHeight(context: Context): Int {
    var result = 0
    try {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return result
}

/**
 * 通过反射获得状态栏的高度
 *
 * @param context 上下文
 * @return 状态栏高度
 */
fun getStatusBarHeightByReflex(context: Context): Int {
    var statusHeight = 0
    try {
        val clazz = Class.forName("com.android.internal.R\$dimen")
        val obj = clazz.newInstance()
        val height = clazz.getField("status_bar_height")[obj].toString().toInt()
        statusHeight = context.resources.getDimensionPixelSize(height)
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
    return statusHeight
}

/**
 * 取得状态栏高度,不推荐使用，因为这个方法依赖于WMS(窗口管理服务的回调)。
 */
fun getStatusBarHeightByWMS(activity: Activity): Int {
    val frame = Rect()
    activity.window.decorView.getWindowVisibleDisplayFrame(frame)
    return frame.top
}

/**
 * 获取是否存在NavigationBar(底部虚拟按键)：
 *
 * @param context 上下文
 * @return true:存在底部虚拟按键
 */
fun checkDeviceHasNavigationBar(context: Context): Boolean {
    var hasNavigationBar = false
    val rs = context.resources
    val id = rs.getIdentifier("config_showNavigationBar", "bool", "android")
    if (id > 0) {
        hasNavigationBar = rs.getBoolean(id)
    }
    try {
        val systemPropertiesClass = Class.forName("android.os.SystemProperties")
        val m = systemPropertiesClass.getMethod("get", String::class.java)
        val navBarOverride = m.invoke(systemPropertiesClass, "qemu.hw.mainkeys") as String
        if ("1" == navBarOverride) {
            hasNavigationBar = false
        } else if ("0" == navBarOverride) {
            hasNavigationBar = true
        }
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
    return hasNavigationBar
}

/**
 * 获取NavigationBar的高度：
 *
 * @param context 上下文
 * @return
 */
fun getNavigationBarHeight(context: Context): Int {
    var navigationBarHeight = 0
    val rs = context.resources
    val id = rs.getIdentifier("navigation_bar_height", "dimen", "android")
    if (id > 0 && checkDeviceHasNavigationBar(context)) {
        navigationBarHeight = rs.getDimensionPixelSize(id)
    }
    return navigationBarHeight
}

/**
 * 通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
 *
 * @param context 上下文
 * @return
 */
fun checkDeviceHasNavigationBar2(context: Context): Boolean {
    val hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey()
    val hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)
    return !hasMenuKey && !hasBackKey
}

/**
 * 获取设备的屏幕密度
 * @param context 上下文
 */
fun getDisplayMetrics(context: Context): DisplayMetrics? {
    val metrics = DisplayMetrics()
    val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
    windowManager?.defaultDisplay?.getRealMetrics(metrics)
    return metrics
}
