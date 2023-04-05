package com.skx.common.permission

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import java.util.*

/**
 * 描述 : 请求权限提示-默认样式，用于展示需要请求权限的目的
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/9/16 5:19 PM
 */
class DefaultReqPermissionTip(private val context: Context?) : ReqPermissionTip {

    companion object {
        val MAP_LABEL_STR: Map<String, String> = HashMap<String, String>().apply {
            put(Manifest.permission.READ_EXTERNAL_STORAGE, "存储")
            put(Manifest.permission.WRITE_EXTERNAL_STORAGE, "存储")
            put(Manifest.permission.ACCESS_COARSE_LOCATION, "位置信息")
            put(Manifest.permission.ACCESS_FINE_LOCATION, "位置信息")
            put(Manifest.permission.READ_PHONE_STATE, "电话")
            put(Manifest.permission.CAMERA, "相机")
        }
    }

    override fun showReqPermissionsReason(permission: List<String>?, negotiate: PermissionNegotiate) {
        val builder = AlertDialog.Builder(context)
        val content = StringBuilder("需要")
        permission?.forEachIndexed { index, s ->
            if (MAP_LABEL_STR.containsKey(s)) {
                content.append(MAP_LABEL_STR[s])
            } else {
                content.append(s)
            }
            if (index < (permission.size - 1)) {
                content.append("、")
            }
        }
        content.append("权限，否则可能会影响您的正常使用")
        builder.setMessage(content)
        builder.setCancelable(false)
        builder.setNegativeButton("取消") { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
            negotiate.termination()
        }
        builder.setPositiveButton("去打开") { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
            negotiate.resume()
        }
        builder.show()
    }
}


/**
 * 描述 : 请求权限提示-用于展示需要请求权限的目的
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/9/16 5:19 PM
 */
interface ReqPermissionTip {

    /**
     * 展示请求权限的原因。
     * 如果需要继续走申请流程，调用{@link #negotiate.resume()}，否则调用{@link #negotiate.termination}。
     * 注意，如果不调用{@link #negotiate}的方法，则不会收到任何授权结果回调。
     *
     * @param permission    请求失败的权限
     * @param negotiate     权限操作谈判类
     */
    fun showReqPermissionsReason(permission: List<String>?, negotiate: PermissionNegotiate)
}