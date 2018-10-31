package com.skx.tomike.aop;

import android.util.Log;

public class Cat implements Animal {
    @Override
    public void eat() {
        Log.e("CAT", "吃鱼");
    }
}
