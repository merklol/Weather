package com.maximapps.maxim_weather.data.network.models

import com.google.gson.annotations.SerializedName

data class Response(
    val cod: String,
    val message: String,
    val city: CityEntity,
    @SerializedName("list")
    val forecastList: List<ForecastEntity>
)
