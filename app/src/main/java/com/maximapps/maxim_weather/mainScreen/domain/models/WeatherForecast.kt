package com.maximapps.maxim_weather.mainScreen.domain.models

import androidx.annotation.DrawableRes
import java.util.Date

data class WeatherForecast(
    val date: Date,
    val temperature: Int,
    val minTemperature: Int,
    val maxTemperature: Int,
    @DrawableRes val weatherIcon: Int
)
