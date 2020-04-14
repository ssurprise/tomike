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
        findViewById(R.id.tv_reflect_methodInvoke).setOnClickListener(this);
        findViewById(R.id.tv_reflect_getFields).setOnClickListener(this);
        findViewById(R.id.tv_reflect_accessField).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_reflect_getClass) {
            test1();

        } else if (v.getId() == R.id.tv_reflect_getConstructors) {
            test2();

        } else if (v.getId() == R.id.tv_reflect_newInstance) {
            test3();

        } else if (v.getId() == R.id.tv_reflect_getMethods) {
            test4();

        } else if (v.getId() == R.id.tv_reflect_methodInvoke) {
            test5();

        } else if (v.getId() == R.id.tv_reflect_getFields) {
            test6();

        } else if (v.getId() == R.id.tv_reflect_accessField) {
            test7();
        }
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
                Log.e(TAG, constructor.toString());
            }

            Constructor<Dog> constructor1 = dogClass.getConstructor(String.class, int.class);
            Log.e(TAG, constructor1.toString());

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
            Method[] methods = dogClass.getMethods();
            for (Method method : methods) {
                Log.e(TAG, "method：" + method.toString());
            }

            Method[] methods2 = dogClass.getDeclaredMethods();
            for (Method method : methods2) {
                Log.e(TAG, "declare method：" + method.toString());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void test5() {
        try {
            Class dogClass = Class.forName("com.skx.tomike.bomberlaboratory.reflect.Dog");

            Method eat = dogClass.getMethod("eat", String.class);
            eat.invoke(dogClass.newInstance(), "大骨头");

            Method hearinng = dogClass.getDeclaredMethod("hearing");
            hearinng.setAccessible(true);
            hearinng.invoke(dogClass.newInstance());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void test6() {
        try {
            Class dogClass = Class.forName("com.skx.tomike.bomberlaboratory.reflect.Dog");

//            Field[] dogFields = dogClass.getFields();
//            for (Field f : dogFields) {
//                Log.e(TAG, f.toString());
//            }

            Field[] dogDeclaredFields = dogClass.getDeclaredFields();
            for (Field f : dogDeclaredFields) {
                Log.e(TAG, f.toString());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void test7() {
        try {
            Class dogClass = Class.forName("com.skx.tomike.bomberlaboratory.reflect.Dog");
            Dog dog = (Dog) dogClass.newInstance();


            Log.e(TAG, "field 修改前为：" + dog.getAge());

            Field age = dogClass.getDeclaredField("age");
            age.setAccessible(true);
            age.setInt(dog, 18);

            Log.e(TAG, "field 修改后为：" + dog.getAge());


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

}
