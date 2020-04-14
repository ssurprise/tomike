package com.skx.tomike.tacticallaboratory.pattern.proxy;

import android.util.Log;

public class AudiCareFactory implements ICarFactory {

    @Override
    public void saleCar() {
        Log.e("汽车工厂", "奥迪 直销");
    }
}
