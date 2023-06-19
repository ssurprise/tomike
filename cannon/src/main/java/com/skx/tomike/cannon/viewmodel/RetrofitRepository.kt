package com.skx.tomike.cannon.viewmodel

import com.skx.common.base.BaseRepository
import com.skx.tomike.cannon.bean.WeatherMini
import com.skx.tomike.cannon.repository.IWeatherService
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.Call

class RetrofitRepository : BaseRepository<IWeatherService>(), IWeatherService {

    override fun querySimpleWeather(cityName: String?): Observable<WeatherMini>? {
        return service?.querySimpleWeather(cityName)
    }

//    override fun querySimpleWeather(body: RequestBody?): Observable<WeatherMini>? {
//        return service?.querySimpleWeather(body)
//    }
//
//    fun querySimpleWeather(cityName: String?): BaseResponse<WeatherMini>? {
//        val map = mutableMapOf<String, String>()
//        map["cityname"] = cityName ?: ""
//        return querySimpleWeather(RequestBody.create(MediaType.parse("application/json"),
//                Gson().toJson(map)))
//    }

    override fun uploadXzApp(body: RequestBody?): Call<String>? {
        return service?.uploadXzApp(body)
    }
}