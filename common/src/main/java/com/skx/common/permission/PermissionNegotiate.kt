package com.skx.common.permission


/**
 * 描述 : 权限-谈判
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/9/16 5:21 PM
 */
interface PermissionNegotiate {

    /**
     * 继续谈判
     */
    fun resume()

    /**
     * 终止谈判
     */
    fun termination()

}