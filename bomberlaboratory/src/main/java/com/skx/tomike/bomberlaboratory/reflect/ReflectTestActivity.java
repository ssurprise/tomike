package com.skx.tomike.bomberlaboratory.reflect;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.skx.tomike.bomberlaboratory.R;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 描述 : 反射demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/13 10:06 AM
 */
public class ReflectTestActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("反射demo").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reflect_test;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test1();
        test2();
        test3();
        test4();
    }

    /**
     * 通过反射获取类的实例
     */
    private void test1() {
        try {
            Class dogClass = Class.forName("com.skx.tomike.bomberlaboratory.reflect.Dog");
            Log.e("Reflect", dogClass.getName());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void test2() {
        try {
            Class<Dog> dogClass = (Class<Dog>) Class.forName("com.skx.tomike.bomberlaboratory.reflect.Dog");

            Constructor<Dog>[] constructors = (Constructor<Dog>[]) dogClass.getConstructors();
            for (Constructor constructor : constructors) {
                Log.e("Reflect", constructor.toString());
            }

            Constructor<Dog> constructor1 = dogClass.getConstructor(String.class, int.class);
            Log.e("Reflect", constructor1.toString());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void test3() {
        try {
            Class dogClass = Class.forName("com.skx.tomike.bomberlaboratory.reflect.Dog");
            Dog dog = (Dog) dogClass.newInstance();
            dog.eat("肉");

            Constructor<Dog> dogConstructor = dogClass.getConstructor(String.class, int.class);
            Dog dog2 = dogConstructor.newInstance("二哈", 3);
            dog2.eat("骨头");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void test4() {
        try {
            Class dogClass = Class.forName("com.skx.tomike.bomberlaboratory.reflect.Dog");
//            Method[] methods = dogClass.getMethods();
//            for (Method method : methods) {
//                Log.e("Reflect", "method：" + method.toString());
//            }
//
//            Method[] methods2 = dogClass.getDeclaredMethods();
//            for (Method method : methods2) {
//                Log.e("Reflect", "declare method：" + method.toString());
//            }

//            Method hearing = dogClass.getMethod("hearing", null);
//            hearing.invoke("hearing", null);


            Method eat = dogClass.getMethod("eat", String.class);
            eat.invoke(dogClass.newInstance(), "大骨头");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
