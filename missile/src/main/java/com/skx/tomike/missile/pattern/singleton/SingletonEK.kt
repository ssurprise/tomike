package com.skx.tomike.missile.pattern.singleton


/**
 * 描述 : 单例 - 静态内部类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/9/27 11:48 上午
 */
class SingletonEK private constructor() {

    object Holder {
        val holder: SingletonEK = SingletonEK()
    }

    companion object {
        val instance = Holder.holder
    }
}

/*
class SingletonDemo private constructor() {
    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder= SingletonDemo()
    }

}
 */