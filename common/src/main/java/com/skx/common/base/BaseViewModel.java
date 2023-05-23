package com.skx.common.base;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 描述 : base ViewModel 类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019/1/25 11:09 上午
 */
public class BaseViewModel<T extends BaseRepository<?>> extends AndroidViewModel {

    protected String TAG = getClass().getSimpleName();

    protected Application mApplication;
    protected T mRepository;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        this.mApplication = application;

        try {
            Type genType = this.getClass().getGenericSuperclass();
            if (genType instanceof ParameterizedType) {
                Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
                if (params.length > 0 && params[0] instanceof Class) {
                    @SuppressWarnings("unchecked")
                    Class<T> clazz = (Class<T>) params[0];
                    mRepository = clazz.newInstance();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "类泛型初始化错误，e=" + e);
            e.printStackTrace();
        }
    }
}
