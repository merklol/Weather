package com.maximapps.maxim_weather.network.models

import com.google.gson.annotations.SerializedName
import com.maximapps.maxim_weather.network.models.City
import com.maximapps.maxim_weather.network.models.Forecast

data class Response(
    val cod: String,
    val message: String,
    val city: City,
    @SerializedName("list")
    val forecastList: List<Forecast>
)
