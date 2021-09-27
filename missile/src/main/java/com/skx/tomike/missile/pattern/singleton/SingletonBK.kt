package com.skx.tomike.missile.pattern.singleton


/**
 * 描述 : 单例 kotlin- 懒汉式
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/9/27 11:37 上午
 */
class SingletonBK private constructor() {

    companion object {
        // kotlin里没有static 的关键字，companion object 就等同于static
        private var holder: SingletonBK? = null
            get() {
                if (field == null) {
                    holder = SingletonBK()
                }
                return field
            }

        fun getInstance(): SingletonBK {
            return holder!!
        }
    }
}