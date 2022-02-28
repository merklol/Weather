package com.maximapps.maxim_weather.mainScreen.domain.models

import java.util.Date

data class Forecast(
    val date: Date,
    val temperature: Int,
    val minTemperature: Int,
    val maxTemperature: Int,
    val iconResId: Int
)
