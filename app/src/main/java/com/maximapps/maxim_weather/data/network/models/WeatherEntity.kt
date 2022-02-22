package com.maximapps.maxim_weather.data.network.models

data class WeatherEntity(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)
