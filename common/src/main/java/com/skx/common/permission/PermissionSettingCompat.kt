package com.skx.common.permission

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import com.skx.common.BuildConfig

/**
 * @author: shiguotao
 * @date:   2022/3/24 5:10 下午
 * @description: 权限设置页适配
 */
object PermissionSettingCompat {

    fun jumpPermissionPage(context: Context): Intent {
        val manufacturer = Build.BRAND.toLowerCase()
        when (manufacturer) {
//            "xiaomi" -> {
//                goXiaoMiManager(context)
//            }
            "huawei" -> {
                return getHuaweiPermissionSettingIntent()
            }
//            "oppo" -> {
//                goOppoManager(context)
//            }
//            "meizu" -> {
//                goMeizuManager(context)
//            }
//            "samsung" -> {
//                goSamsungManager(context)
//            }
//            "sony" -> {
//                goSonyManager(context)
//            }
//            "lg" -> {
//                goLGManager(context)
//            }
//            "letv" -> {
//                goLetvManager(context)
//            }
//            "qiku", "360" -> {
//                go360Manager(context)
//            }
////            "vivo" -> {}//vivo
////            "yulong" -> {}//酷派
////            "lenovo" -> {}//联想
////            "zte" -> {}//中兴
////            "google" -> {}//谷歌
            else -> {
              return  Intent()
//                goAppDetailSetting(context)
            }
        }
    }

    /**
     * 华为权限管理
     */
    private fun getHuaweiPermissionSettingIntent(): Intent {
        val intent = Intent()
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
        val comp = ComponentName(
            "com.huawei.systemmanager",
            "com.huawei.permissionmanager.ui.MainActivity"
        )
        intent.component = comp
        return intent
    }

    private fun getOppoPermissionSettingIntent():Intent{
        val intent = Intent()
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
//        val comp = ComponentName(
//            "com.color.safecenter",
//            "com.color.safecenter.permission.PermissionManagerActivity"
//        )
//        intent.component = comp;
        return intent
    }

    //
    //————————————————
    //版权声明：本文为CSDN博主「发型不给力」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
    //原文链接：https://blog.csdn.net/luckrr/article/details/78211465

    //小米系统有以下几个版本需要适配
//    private fun goXiaoMiManager(context: Context) {
//        var rom: String? = getMiuiVersion()
//        try {
//            var intent: Intent = Intent()
//            if ("V5" == rom) {
//                val packageURI = Uri.parse("package:" + BuildConfig.APPLICATION_ID)
//                intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI)
//            } else if ("V6" == rom || "V7" == rom) {
//                intent.action = "miui.intent.action.APP_PERM_EDITOR"
//                intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity")
//                intent.putExtra("extra_pkgname", BuildConfig.APPLICATION_ID)
//            } else if ("V8" == rom || "V9" == rom) {
//                intent.action = "miui.intent.action.APP_PERM_EDITOR"
//                intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity")
//                intent.putExtra("extra_pkgname", BuildConfig.APPLICATION_ID)
//            } else {
//                goAppDetailSetting(context)
//            }
//            context.startActivity(intent)
//        } catch (e: Exception) {
//            goAppDetailSetting(context)
//        }
//    }

}