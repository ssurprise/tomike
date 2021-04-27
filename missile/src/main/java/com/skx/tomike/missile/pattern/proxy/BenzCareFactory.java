package com.skx.tomike.missile.pattern.proxy;

import android.util.Log;

/**
 * 描述 : 奔驰汽车工厂
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/14 10:33 AM
 */
public class BenzCareFactory implements ICarFactory {

    @Override
    public void saleCar() {
        Log.e("汽车工厂", "奔驰 直销");
    }
}
