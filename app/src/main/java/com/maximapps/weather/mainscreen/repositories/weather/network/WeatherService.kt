package com.maximapps.weather.mainscreen.repositories.weather.network

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("forecast?&units=metric")
    suspend fun fetchWeatherForecast(@Query("q") cityName: String): Response

    @GET("forecast?&units=metric")
    suspend fun fetchWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): Response
}
