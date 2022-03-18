package com.maximapps.maxim_weather.mainScreen.usecases.models

import androidx.annotation.DrawableRes
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
    @DrawableRes val weatherIcon: Int,
    val forecastList: List<WeatherForecast>
)
