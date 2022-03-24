package com.skx.common.permission

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

/**
 * 描述 : 请求权限提示-用于展示需要请求权限的目的
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/9/16 5:19 PM
 */
class DefaultReqPermissionTip(private val context: Context?) : ReqPermissionTip {

    override fun showReqPermissionsReason(
        permission: Array<String>?,
        negotiate: PermissionNegotiate
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("xx权限请求失败....需要手动打开")
        builder.setCancelable(false)
        builder.setNegativeButton("取消") { dialog: DialogInterface, which: Int ->
            dialog.dismiss()
            negotiate.resume()
        }
        builder.setPositiveButton("去打开") { dialog: DialogInterface, which: Int ->
            dialog.dismiss()
            negotiate.resume()
        }
        builder.show()
    }
}


interface ReqPermissionTip {

    /**
     * 展示请求权限的原因。
     * 如果需要继续走申请流程，调用{@link #negotiate.resume()}，否则调用{@link #negotiate.termination}。
     * 这两个方法如果不执行，可能会收不到授权结果回调。
     *
     * @param permission    请求失败的权限
     * @param negotiate     权限操作谈判类
     */
    fun showReqPermissionsReason(permission: Array<String>?, negotiate: PermissionNegotiate)
}