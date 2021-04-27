package com.skx.tomike.cannon.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 描述 : 城市天气
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2016/9/5
 */
public class WeatherMini {

    public WeatherInfo sk;

    public static class WeatherInfo {
        @SerializedName(value = "temp", alternate = {"temperature"})
        public String temperature;// 温度
        public String humidity;// 湿度
        public String time;// 更新时间
        @SerializedName(value = "wind_direction", alternate = {"windDirection", "direction"})
        public String windDirection;// 风向，可能为空
        @SerializedName(value = "wind_strength", alternate = {"windPower", "power"})
        public String windPower;// 风力，可能为空
    }
}
