package com.skx.tomike.cannon.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.skx.common.base.BaseObserver;
import com.skx.common.base.BaseViewModel;
import com.skx.common.net.exception.ExceptionHandle;
import com.skx.common.utils.ToastTool;
import com.skx.tomike.cannon.bean.WeatherMini;

import org.jetbrains.annotations.Nullable;

import io.reactivex.Observable;

/**
 * 描述 : Retrofit demo viewholder
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/24 2:11 PM
 */
public class RetrofitViewModel extends BaseViewModel<RetrofitRepository> {

    private final MutableLiveData<WeatherMini> mWeatherLiveData = new MutableLiveData<>();

    public RetrofitViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<WeatherMini> getWeatherLiveData() {
        return mWeatherLiveData;
    }

    public void queryCityWeather(String cityName) {
        Observable<WeatherMini> weatherMiniObservable = mRepository.querySimpleWeather(cityName);
        if (weatherMiniObservable != null) {
            subscribeDisposable(new BaseObserver<WeatherMini>(weatherMiniObservable) {

                @Override
                protected void onStart() {
                    super.onStart();
                    Log.e(TAG, "onStart");
                }

                @Override
                public void doOnNext(WeatherMini response) {
                    Log.e(TAG, "doOnNext");
                    mWeatherLiveData.setValue(response);
                }

                @Override
                public void doOnError(@Nullable ExceptionHandle.ResponseThrowable e) {
                    ToastTool.showToast(mApplication, e != null ? e.msg : null);
                }
            });
        }
    }
}
