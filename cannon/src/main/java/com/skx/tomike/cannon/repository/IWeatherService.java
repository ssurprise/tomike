package com.skx.tomike.cannon.repository;

import com.skx.tomike.cannon.bean.BaseBean;
import com.skx.tomike.cannon.bean.WeatherMini;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 描述 : 城市天气service
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2016/9/5
 */
public interface IWeatherService {

    @GET("weather/index?format=2&key=487a0e269f87aa5c666ebf17b40e80f3")
    Call<BaseBean<WeatherMini>> querySimpleWeather(@Query("cityname") String cityName);


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("app/upload")
    Call<String> uploadXzApp(@Body RequestBody body);

}
