package com.skx.tomike.cannon.aop;

import android.util.Log;

public class Cat implements Animal {
    @Override
    public void eat() {
        Log.e("CAT", "吃鱼");
    }
}
