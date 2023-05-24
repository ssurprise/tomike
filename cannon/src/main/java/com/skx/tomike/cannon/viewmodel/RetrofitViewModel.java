package com.skx.tomike.cannon.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.skx.common.base.BaseObserver;
import com.skx.common.base.BaseViewModel;
import com.skx.common.utils.ToastTool;
import com.skx.tomike.cannon.bean.BaseBean;
import com.skx.tomike.cannon.bean.WeatherMini;
import com.skx.tomike.cannon.repository.IWeatherService;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
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
public class RetrofitViewModel extends BaseViewModel<RetrofitRepository> {

    private final MutableLiveData<BaseBean<WeatherMini>> mWeatherLiveData = new MutableLiveData<>();

    public RetrofitViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<BaseBean<WeatherMini>> getWeatherLiveData() {
        return mWeatherLiveData;
    }

    public void queryCityWeather(String cityName) {
        subscribeDisposable(new BaseObserver<BaseBean<WeatherMini>>(mRepository.querySimpleWeather(cityName)) {
            @Override
            protected void onStart() {
                super.onStart();
                Log.e(TAG,"onStart");
            }

            @Override
            public void doOnNext(BaseBean<WeatherMini> response) {
                Log.e(TAG,"doOnNext");

                if ("200".equalsIgnoreCase(response.resultCode)) {
                    mWeatherLiveData.setValue(response);
                } else {
                    ToastTool.showToast(mApplication, response.reason);
                }
            }

            @Override
            public void onError(@NotNull Throwable e) {
                super.onError(e);
                Log.e(TAG,"doOnNext");
            }
        });
    }

    public void uploadXzApp() {

        /*
        接口：http://apk-upload.ops.xiaozhu.com/app/upload/
        方法：post
        body:
            {
              "pkgurl": ["url1","url2"],
              "mainChannels": ["xiaozhu","xiaomi","huawei"], // 主渠道包，不包含url 中的名字即可。因为运维那边不是做的全匹配，是包含关系。
              "env": "prod"
              "delay_sec_2": 30
            }
        ContentType: application/json
         */
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://apk-upload.ops.xiaozhu.com/")
                .addConverterFactory(GsonConverterFactory.create())//使用了gson去解析json
                .build();
        IWeatherService weather = retrofit.create(IWeatherService.class);

        HashMap<String, String> map = new HashMap<>();

        String[] pkgurl = {"", ""};
        String[] mainChannels = {"xiaozhu", "xiaomi", "huawei"};

        map.put("pkgurl", Arrays.toString(pkgurl));
        map.put("mainChannels", Arrays.toString(mainChannels));
        map.put("env", "prod");
        map.put("delay_sec_2", "30");

        Call<String> resp = weather.uploadXzApp(RequestBody.create(MediaType.parse("application/json"),
                new Gson().toJson(map)));

        resp.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
}
