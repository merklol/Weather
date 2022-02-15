package com.maximapps.weatherapp.data.network

import com.maximapps.weatherapp.data.network.models.Response
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface WeatherService {
    @GET("forecast?q=London&appid=40116f7c79b9c42fd92351dcaf9c70d6")
    fun getForecast(): Observable<Response>
}