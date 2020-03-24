package com.skx.tomike.cannonlaboratory.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.skx.tomike.cannonlaboratory.bean.BaseBean;
import com.skx.tomike.cannonlaboratory.bean.WeatherMini;
import com.skx.tomike.cannonlaboratory.repository.IWeatherService;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.utils.ToastTool;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 描述 : Retrofit demo viewholder
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/24 2:11 PM
 */
public class RetrofitViewModel extends BaseViewModel {

    private final MutableLiveData<BaseBean<WeatherMini>> mWeatherLiveData = new MutableLiveData<>();

    public RetrofitViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<BaseBean<WeatherMini>> getWeatherLiveData() {
        return mWeatherLiveData;
    }

    public void queryCityWeather(String cityName) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://v.juhe.cn/")
                .addConverterFactory(GsonConverterFactory.create())//使用了gson去解析json
                .build();
        IWeatherService weather = retrofit.create(IWeatherService.class);

        Call<BaseBean<WeatherMini>> resp = weather.querySimpleWeather(cityName);

        resp.enqueue(new Callback<BaseBean<WeatherMini>>() {
            @Override
            public void onResponse(Call<BaseBean<WeatherMini>> call, Response<BaseBean<WeatherMini>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    BaseBean<WeatherMini> body = response.body();

                    if ("200".equalsIgnoreCase(body.resultCode)) {
                        mWeatherLiveData.setValue(body);
                    } else {
                        ToastTool.showToast(mApplication, body.reason);
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseBean<WeatherMini>> call, Throwable t) {
            }
        });
    }
}
