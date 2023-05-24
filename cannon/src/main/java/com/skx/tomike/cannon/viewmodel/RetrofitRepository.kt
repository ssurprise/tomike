package com.skx.tomike.cannon.viewmodel

import com.skx.common.base.BaseRepository
import com.skx.tomike.cannon.bean.BaseBean
import com.skx.tomike.cannon.bean.WeatherMini
import com.skx.tomike.cannon.repository.IWeatherService
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.Call

class RetrofitRepository: BaseRepository<IWeatherService>(),IWeatherService {

    override fun querySimpleWeather(cityName: String?): Observable<BaseBean<WeatherMini>>? {
       return service?.querySimpleWeather(cityName)
    }

    override fun uploadXzApp(body: RequestBody?): Call<String>? {
       return service?.uploadXzApp(body)
    }
}