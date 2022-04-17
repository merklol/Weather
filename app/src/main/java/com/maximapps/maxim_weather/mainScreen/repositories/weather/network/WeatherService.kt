package com.maximapps.maxim_weather.mainScreen.repositories.weather.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("forecast?&units=metric")
    fun fetchWeatherForecast(@Query("q") cityName: String): Single<Response>

    @GET("forecast?&units=metric")
    fun fetchWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): Single<Response>
}
