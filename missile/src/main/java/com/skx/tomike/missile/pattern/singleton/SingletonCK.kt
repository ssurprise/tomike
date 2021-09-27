package com.skx.tomike.missile.pattern.singleton

/**
 * 描述 : 单例 - 懒汉式 - 线程安全
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/9/27 11:37 上午
 */
class SingletonCK private constructor() {

    companion object {
        // kotlin里没有static 的关键字，companion object 就等同于static
        private var holder: SingletonCK? = null
            get() {
                if (field == null) {
                    holder = SingletonCK()
                }
                return field
            }

        // 在Kotlin中，如果需要将方法声明为同步，需要添加@Synchronized注解。
        @Synchronized
        fun getInstance(): SingletonCK {
            return holder!!
        }
    }
}