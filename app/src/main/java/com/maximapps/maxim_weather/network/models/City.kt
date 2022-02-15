package com.maximapps.maxim_weather.network.models

import com.google.gson.annotations.SerializedName


data class City(
    val id: Int,
    val name: String,
    @SerializedName("coord")
    val coordinates: Coordinates,
    val country: String,
    val timezone: Int,
    val sunrise: Int,
    val sunset: Int
)
