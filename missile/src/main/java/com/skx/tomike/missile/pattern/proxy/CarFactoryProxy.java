package com.skx.tomike.missile.pattern.proxy;

import android.util.Log;

/**
 * 描述 : 汽车工厂的代理
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/14 10:31 AM
 */
public class CarFactoryProxy implements ICarFactory {

    private ICarFactory iCarFactory;

    public void setProxy(ICarFactory carFactory) {
        this.iCarFactory = carFactory;
    }

    @Override
    public void saleCar() {
        if (iCarFactory == null) {
            throw new IllegalArgumentException("被代理对象未设置");
        }
        doSomethingBefore();
        iCarFactory.saleCar();
        doSomethingAfter();
    }

    private void doSomethingBefore() {
        // do something
        Log.e("静态代理", "有优惠哦~");
    }

    private void doSomethingAfter() {
        // do something
        Log.e("静态代理", "售后更方便哦~");
    }
}
