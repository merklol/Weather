package com.maximapps.maxim_weather.domain.models

import java.util.Date

data class DetailedForecast(
    val date: Date,
    val temperature: Int,
    val temperatureMin: Int,
    val temperatureMax: Int,
    val wind: Int,
    val feelsLike: Int,
    val weather: String,
    val iconResId: Int,
    val details: List<Forecast>
)
