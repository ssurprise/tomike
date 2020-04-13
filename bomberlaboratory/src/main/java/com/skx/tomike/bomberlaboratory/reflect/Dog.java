package com.skx.tomike.bomberlaboratory.reflect;

import android.util.Log;

public class Dog extends Animal {

    public String name;
    private int age;

    public Dog() {
    }

    public Dog(String name) {
        this.name = name;
    }

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void hearing() {
        Log.e("Reflect", "听力很好");
    }

    public void run() {
        Log.e("Reflect", "跑");
    }

    @Override
    public void eat(String food) {
        Log.e("Reflect", "狗吃" + food);
    }
}
