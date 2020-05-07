package com.skx.tomike.bomberlaboratory.reflect;

import android.util.Log;

import androidx.annotation.IntRange;

public class Dog extends Animal {

    @ReflectAnnotationField
    public String name = "旺财";
    @IntRange(from = 0, to = 20)
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

    protected void eatBone() {
        Log.e("Reflect", "protected 修饰的方法 -> 狗吃骨头");
    }

    void friend() {
        Log.e("Reflect", "包方法 -> 狗是人类的好朋友");
    }

    @ReflectAnnotationMethod
    public void run() {
        Log.e("Reflect", "自己的public 方法 -> 跑");
    }

    @ReflectAnnotationMethod
    @Override
    public void eat(@ReflectAnnotationParam String food) {
        Log.e("Reflect", "重写父类的方法 -> 狗吃" + food);
    }
}
