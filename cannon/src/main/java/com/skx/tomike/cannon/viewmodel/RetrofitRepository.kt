package com.skx.tomike.cannon.viewmodel

import com.google.gson.Gson
import com.skx.common.base.BaseRepository
import com.skx.common.net.BaseResponse
import com.skx.tomike.cannon.bean.WeatherMini
import com.skx.tomike.cannon.repository.IWeatherService
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call

class RetrofitRepository : BaseRepository<IWeatherService>(), IWeatherService {

    override fun querySimpleWeather(body: RequestBody?): Call<BaseResponse<WeatherMini>>? {
        return service?.querySimpleWeather(body)
    }

    fun querySimpleWeather(cityName: String?): Call<BaseResponse<WeatherMini>>? {
        val map = mutableMapOf<String, String>()
        map["cityname"] = cityName ?: ""
        return querySimpleWeather(RequestBody.create(MediaType.parse("application/json"),
                Gson().toJson(map)))
    }

    override fun uploadXzApp(body: RequestBody?): Call<String>? {
        return service?.uploadXzApp(body)
    }
}