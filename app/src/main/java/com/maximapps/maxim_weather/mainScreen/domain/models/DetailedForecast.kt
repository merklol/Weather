package com.maximapps.maxim_weather.mainScreen.domain.models

import java.util.Date

const val Undefined = "undefined"

data class DetailedForecast(
    val date: Date,
    val temperature: Int,
    val temperatureMin: Int,
    val temperatureMax: Int,
    val wind: Int,
    val feelsLike: Int,
    val weatherCondition: String,
    val iconResId: Int,
    val temperatureList: List<Temperature>
)
