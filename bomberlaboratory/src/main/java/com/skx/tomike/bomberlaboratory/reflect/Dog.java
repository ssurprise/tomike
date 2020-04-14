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
        Log.e("Reflect", "private 修饰的方法 -> 狗的听觉非常棒");
    }

    public void run() {
        Log.e("Reflect", "自己的public 方法 -> 跑");
    }

    @Override
    public void eat(String food) {
        Log.e("Reflect", "重写父类的方法 -> 狗吃" + food);
    }
}
