package com.skx.tomike.missile.pattern.singleton;

/**
 * 描述 : 单例 - 懒汉式
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/9/27 11:37 上午
 */
class SingletonB {

    /*
    是否 Lazy 初始化：是
    是否多线程安全：否
    实现难度：易
    描述：这种方式是最基本的实现方式，这种实现最大的问题就是不支持多线程。因为没有加锁 synchronized，
         所以严格意义上它并不算单例模式。这种方式 lazy loading 很明显，不要求线程安全，在多线程不能正常工作。
     */

    private static SingletonB instance = null;

    private SingletonB() {

    }

    public static SingletonB getInstance() {
        if (instance == null) {
            instance = new SingletonB();
        }
        return instance;
    }
}
