package com.skx.common.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 描述 : MVVM 架构基础activity
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019/1/25 11:09 上午
 */
public abstract class BaseMvvmActivity<T extends BaseViewModel<?>> extends AppCompatActivity {

    protected Context mActivity;
    protected T mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        initViewModel();
    }

    private void initViewModel() {
        try {
            Type genType = this.getClass().getGenericSuperclass();
            if (genType instanceof ParameterizedType) {
                Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
                if (params.length > 0 && params[0] instanceof Class) {
                    @SuppressWarnings("unchecked")
                    Class<T> clazz = (Class<T>) params[0];
                    mViewModel = new ViewModelProvider(this).get(clazz);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroy() {
        if (mViewModel != null) {
            mViewModel = null;
        }
        mActivity = null;
        super.onDestroy();
    }
}