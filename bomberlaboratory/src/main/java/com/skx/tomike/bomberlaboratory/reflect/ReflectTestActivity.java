package com.skx.tomike.bomberlaboratory.reflect;

import android.util.Log;
import android.view.View;

import com.skx.tomike.bomberlaboratory.R;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 描述 : 反射demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/13 10:06 AM
 */
public class ReflectTestActivity extends SkxBaseActivity<BaseViewModel> implements View.OnClickListener {

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
        findViewById(R.id.tv_reflect_getClass).setOnClickListener(this);
        findViewById(R.id.tv_reflect_getConstructors).setOnClickListener(this);
        findViewById(R.id.tv_reflect_newInstance).setOnClickListener(this);
        findViewById(R.id.tv_reflect_getMethods).setOnClickListener(this);
        findViewById(R.id.tv_reflect_getMethod).setOnClickListener(this);
        findViewById(R.id.tv_reflect_methodInvoke).setOnClickListener(this);
        findViewById(R.id.tv_reflect_getFields).setOnClickListener(this);
        findViewById(R.id.tv_reflect_accessField).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_reflect_getClass) {
            test1();

        } else if (v.getId() == R.id.tv_reflect_getConstructors) {
            getConstructors();

        } else if (v.getId() == R.id.tv_reflect_newInstance) {
            newInstance();

        } else if (v.getId() == R.id.tv_reflect_getMethods) {
            getMethods();

        } else if (v.getId() == R.id.tv_reflect_getMethod) {
            getMethod();

        } else if (v.getId() == R.id.tv_reflect_methodInvoke) {
            invokeMethod();

        } else if (v.getId() == R.id.tv_reflect_getFields) {
            getFields();

        } else if (v.getId() == R.id.tv_reflect_accessField) {
            accessField();

        } else if (v.getId() == R.id.tv_reflect_annotation) {
            getAnnotation();
        }
    }


    /**
     * 通过反射获取类的实例
     */
    @SuppressWarnings("unchecked")
    private void test1() {
        try {
            Class<Dog> dogClass = (Class<Dog>) Class.forName("com.skx.tomike.bomberlaboratory.reflect.Dog");
            Log.e("Reflect", dogClass.getName());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void getConstructors() {
        try {
            Class<Dog> dogClass = (Class<Dog>) Class.forName("com.skx.tomike.bomberlaboratory.reflect.Dog");

            Constructor<Dog>[] constructors = (Constructor<Dog>[]) dogClass.getConstructors();
            for (Constructor<Dog> constructor : constructors) {
                Log.e(TAG, constructor.toString());
            }

            Constructor<Dog> constructor1 = dogClass.getConstructor(String.class, int.class);
            Log.e(TAG, constructor1.toString());

        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void newInstance() {
        try {
            Class<Dog> dogClass = (Class<Dog>) Class.forName("com.skx.tomike.bomberlaboratory.reflect.Dog");
            Dog dog = (Dog) dogClass.newInstance();
            dog.eat("肉");

            Constructor<Dog> dogConstructor = dogClass.getConstructor(String.class, int.class);
            Dog dog2 = dogConstructor.newInstance("二哈", 3);
            dog2.eat("骨头");

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void getMethods() {
        try {
            Class<Dog> dogClass = (Class<Dog>) Class.forName("com.skx.tomike.bomberlaboratory.reflect.Dog");
            // 获取类的所有公共(public 修饰)方法，包括超类中的公共方法。private/protect 修饰的方法获取不到。
            Method[] methods = dogClass.getMethods();
            for (Method method : methods) {
                Log.e(TAG, "method：" + method.toString());
            }

            // 获取类的所有方法，不包括超类超接口中的方法。private/protect/public 修饰的方法。
            Method[] methods2 = dogClass.getDeclaredMethods();
            for (Method method : methods2) {
                Log.e(TAG, "declare method：" + method.toString());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void getMethod() {
        try {
            Class<Dog> dogClass = (Class<Dog>) Class.forName("com.skx.tomike.bomberlaboratory.reflect.Dog");

            Method eat = dogClass.getMethod("eat", String.class);
            Log.e(TAG, "getMethod获取Dog类的public方法：" + eat.toString());

            Method sire = dogClass.getMethod("sire");
            Log.e(TAG, "getMethod获取Dog超类的public方法：" + sire.toString());


            // private 修饰的方法
            Method hearing = dogClass.getDeclaredMethod("hearing");
            Log.e(TAG, "getDeclaredMethod获取Dog类的private方法：" + hearing.toString());

            // protected 修饰的方法
            Method eatBone = dogClass.getDeclaredMethod("eatBone");
            Log.e(TAG, "getDeclaredMethod获取Dog类的protected方法：" + eatBone.toString());

            // package-private 修饰的方法
            Method friend = dogClass.getDeclaredMethod("friend");
            Log.e(TAG, "getDeclaredMethod获取Dog类的package-private方法：" + friend.toString());

        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void invokeMethod() {
        try {
            Class<Dog> dogClass = (Class<Dog>) Class.forName("com.skx.tomike.bomberlaboratory.reflect.Dog");

            Method eat = dogClass.getMethod("eat", String.class);
            eat.invoke(dogClass.newInstance(), "大骨头");

            Method hearinng = dogClass.getDeclaredMethod("hearing");
            hearinng.setAccessible(true);
            hearinng.invoke(dogClass.newInstance());

        } catch (ClassNotFoundException | InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void getFields() {
        try {
            Class<Dog> dogClass = (Class<Dog>) Class.forName("com.skx.tomike.bomberlaboratory.reflect.Dog");

            Field[] dogFields = dogClass.getFields();
            for (Field f : dogFields) {
                Log.e(TAG, "getFields：" + f.toString());
            }

            Log.e(TAG, "---------------------------------------分割线------------------------------------------------");

            Field[] dogDeclaredFields = dogClass.getDeclaredFields();
            for (Field f : dogDeclaredFields) {
                Log.e(TAG, "getDeclaredFields：" + f.toString());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void accessField() {
        try {
            Class<Dog> dogClass = (Class<Dog>) Class.forName("com.skx.tomike.bomberlaboratory.reflect.Dog");

            Dog dog = (Dog) dogClass.newInstance();

            Log.e(TAG, "name 修改前为：" + dog.name);
            Field name = dogClass.getField("name");
            name.set(dog, "二哈");
            Log.e(TAG, "name 修改前为：" + dog.name);

            Log.e(TAG, "---------------------------------------分割线------------------------------------------------");


            Log.e(TAG, "age 修改前为：" + dog.getAge());
            Field age = dogClass.getDeclaredField("age");
            age.setAccessible(true);
            age.setInt(dog, 18);

            Log.e(TAG, "age 修改后为：" + dog.getAge());

        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void getAnnotation() {
        try {
            Class<Dog> dogClass = (Class<Dog>) Class.forName("com.skx.tomike.bomberlaboratory.reflect.Dog");
            Dog dog = (Dog) dogClass.newInstance();


        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }
}
