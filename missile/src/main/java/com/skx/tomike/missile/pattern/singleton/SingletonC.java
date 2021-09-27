package com.skx.tomike.missile.pattern.singleton;

/**
 * 描述 : 单例 - 懒汉式 - 线程安全
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/9/27 11:37 上午
 */
class SingletonC {

    /*
    是否 Lazy 初始化：是
    是否多线程安全：是
    实现难度：易
    描述：这种方式具备很好的 lazy loading，能够在多线程中很好的工作，但是，效率很低，99% 情况下不需要同步。
    优点：第一次调用才初始化，避免内存浪费。
    缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率。getInstance() 的性能对应用程序
         不是很关键（该方法使用不太频繁）。

     */

    private static SingletonC instance = null;

    private SingletonC() {

    }

    public static synchronized SingletonC getInstance() {
        if (instance == null) {
            instance = new SingletonC();
        }
        return instance;
    }
}
