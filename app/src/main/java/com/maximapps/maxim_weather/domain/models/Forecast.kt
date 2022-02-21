package com.maximapps.maxim_weather.domain.models

import java.util.Date

data class DetailedForecast(
    val date: Date,
    val temperature: Int,
    val minTemperature: Int,
    val maxTemperature: Int,
    val iconResId: Int
)