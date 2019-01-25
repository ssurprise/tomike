package com.skx.tomikecommonlibrary.base;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseActivity<T extends BaseViewModel> extends AppCompatActivity {

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
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            @SuppressWarnings("unchecked")
            Class<T> clazz = (Class<T>) params[0];

            if (clazz != null) {
                mViewModel = ViewModelProviders.of(this).get(clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
