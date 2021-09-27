package com.skx.tomike.missile.pattern.singleton


/**
 * 描述 : 单例 - 双检锁/双重校验锁（DCL，即 double-checked locking）
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/9/27 11:37 上午
 */
class SingletonDK private constructor() {

    companion object {
        val instance: SingletonDK by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            SingletonDK()
        }
    }
}