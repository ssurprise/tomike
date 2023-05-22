package com.skx.common.base;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseViewModel<T extends IRepository> extends AndroidViewModel {

    protected String TAG = getClass().getSimpleName();


    protected Application mApplication;
    protected T mRepository;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        this.mApplication = application;

        try {
            Type genType = this.getClass().getGenericSuperclass();
            if (genType != null) {
                Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
                if (params.length > 0) {
                    @SuppressWarnings("unchecked")
                    Class<T> clazz = (Class<T>) params[0];

                    if (clazz != null) {
                        mRepository = clazz.newInstance();
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "类泛型初始化错误，e=" + e);
            e.printStackTrace();
        }
    }
}
