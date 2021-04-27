package com.skx.tomike.cannon.aop;

import android.util.Log;

public class Dog implements Animal {
    @Override
    public void eat() {
        Log.e("DOG","吃骨头");
    }
}
