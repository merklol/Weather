package com.maximapps.maxim_weather.data.network.models

import com.google.gson.annotations.SerializedName


data class CityEntity(
    val id: Int,
    val name: String,
    @SerializedName("coord")
    val coordinates: CoordinatesEntity,
    val country: String,
    val timezone: Int,
    val sunrise: Int,
    val sunset: Int
)
