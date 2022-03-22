package com.skx.common.permission

/**
 * 描述 : 请求权限提示-用于展示需要请求权限的目的
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/9/16 5:19 PM
 */
class DefaultReqPermissionTip : ReqPermissionTip {
    override fun ok() {

    }

    override fun reject() {
    }
}


interface ReqPermissionTip {
    fun ok()
    fun reject()
}