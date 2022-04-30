package com.maximapps.weather.mainScreen.usecases.common

data class WeatherData(
    val cityName: String = "",
    val detailedForecast: List<DetailedForecast> = emptyList()
)
