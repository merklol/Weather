package com.maximapps.maxim_weather.network

import com.maximapps.maxim_weather.network.models.Response
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("forecast?")
    fun getForecast(@Query("q") cityName: String): Observable<Response>
}
