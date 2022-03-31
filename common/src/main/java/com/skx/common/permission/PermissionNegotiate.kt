package com.skx.common.permission


/**
 * 描述 : 权限-谈判类，用于请求失败时二次沟通
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/9/16 5:21 PM
 */
interface PermissionNegotiate {

    /**
     * 继续谈判，用于选择继续进行授权
     */
    fun resume()

    /**
     * 终止谈判，用于停止/取消授权操作
     */
    fun termination()

}