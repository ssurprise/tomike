package com.skx.tomike.interf;

import com.skx.tomike.javabean.RespContent;
import com.skx.tomike.javabean.WeatherMini;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shiguotao on 2016/8/31.
 */
public interface IGetNetData {

    //通过注解设置请求方式
    @GET("weather_mini")
    // 设置请求方法为get，相对路径为注解内内容，其中{test}会被@Path注解指定内容替换   @Query用于指定参数
    Call<RespContent<WeatherMini>> getWeatherFromNetByGet(@Query("city") String city);
}
