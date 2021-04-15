package com.skx.tomike.cannonlaboratory.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.skx.common.base.BaseViewModel;
import com.skx.common.utils.ToastTool;
import com.skx.tomike.cannonlaboratory.bean.BaseBean;
import com.skx.tomike.cannonlaboratory.bean.WeatherMini;
import com.skx.tomike.cannonlaboratory.repository.IWeatherService;

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

        map.put("mainChannels", "prod");
        map.put("env", "prod");

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
