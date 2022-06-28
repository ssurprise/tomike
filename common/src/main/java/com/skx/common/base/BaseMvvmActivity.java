package com.skx.common.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseMvvmActivity<T extends BaseViewModel> extends AppCompatActivity {

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
                mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance().create(clazz);
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
