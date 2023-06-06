package com.skx.tomike.cannon.repository;

import com.skx.common.net.BaseResponse;
import com.skx.tomike.cannon.bean.WeatherMini;

import io.reactivex.Observable;
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


//    @Headers({"base_url: http://v.juhe.cn"})
//    @GET("weather/index?format=2&key=487a0e269f87aa5c666ebf17b40e80f3")
//    Observable<BaseResponse<WeatherMini>> querySimpleWeather(@Query("cityname") String cityName);


//   @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @FormUrlEncoded
//    @POST("http://v.juhe.cn/weather/index?format=2&key=487a0e269f87aa5c666ebf17b40e80f3")
//    Call<BaseResponse<WeatherMini>> querySimpleWeather(@Field("cityname") String cityName);


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("http://v.juhe.cn/weather/index?format=2&key=487a0e269f87aa5c666ebf17b40e80f3")
    Call<BaseResponse<WeatherMini>> querySimpleWeather(@Body RequestBody body);


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("app/upload")
    Call<String> uploadXzApp(@Body RequestBody body);

}
