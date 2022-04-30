package com.maximapps.weather.mainscreen.usecases.common

data class WeatherData(
    val cityName: String = "",
    val detailedForecast: List<DetailedForecast> = emptyList()
)
