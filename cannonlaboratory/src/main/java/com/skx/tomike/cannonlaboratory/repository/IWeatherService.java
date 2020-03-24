package com.skx.tomike.cannonlaboratory.repository;

import com.skx.tomike.cannonlaboratory.bean.BaseBean;
import com.skx.tomike.cannonlaboratory.bean.WeatherMini;

import retrofit2.Call;
import retrofit2.http.GET;
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

}
