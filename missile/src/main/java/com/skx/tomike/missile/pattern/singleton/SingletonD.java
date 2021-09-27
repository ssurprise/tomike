package com.skx.tomike.missile.pattern.singleton;

/**
 * 描述 : 单例 - 双检锁/双重校验锁（DCL，即 double-checked locking）
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/9/27 11:37 上午
 */
class SingletonD {

    /*
    是否 Lazy 初始化：是
    是否多线程安全：是
    实现难度：较复杂
    描述：这种方式采用双锁机制，安全且在多线程情况下能保持高性能。getInstance() 的性能对应用程序很关键。
     */

    private static volatile SingletonD instance = null;

    private SingletonD() {

    }

    public static SingletonD getInstance() {
        if (instance == null) {
            synchronized (SingletonD.class) {
                if(instance==null){
                    instance = new SingletonD();
                }
            }
        }
        return instance;
    }
}
