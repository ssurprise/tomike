package com.skx.tomike.missile.pattern.proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 描述 : 汽车工厂的代理
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/14 10:31 AM
 */
public class CarFactoryProxyPro implements InvocationHandler {

    /**
     * 持有的真实对象
     */
    private Object mFactory;

    public void setProxy(Object factory) {
        this.mFactory = factory;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(mFactory.getClass().getClassLoader(),
                mFactory.getClass().getInterfaces(), this);
    }

    /**
     * 动态代理方法
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doSomethingBefore();

        Object invoke = method.invoke(mFactory, args);
        doSomethingAfter();
        return invoke;
    }

    private void doSomethingBefore() {
        // do something
        Log.e("动态代理", "有优惠哦~");
    }

    private void doSomethingAfter() {
        // do something
        Log.e("动态代理", "售后更方便哦~");
    }

}
