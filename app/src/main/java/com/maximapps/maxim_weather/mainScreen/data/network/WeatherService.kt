package com.maximapps.maxim_weather.mainScreen.data.network

import com.maximapps.maxim_weather.mainScreen.data.network.models.Response
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("forecast?&units=metric")
    fun getForecast(@Query("q") cityName: String): Single<Response>
}
