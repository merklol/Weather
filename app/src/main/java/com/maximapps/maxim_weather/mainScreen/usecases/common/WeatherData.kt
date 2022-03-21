package com.maximapps.maxim_weather.mainScreen.usecases.models

data class WeatherData(
    val cityName: String = "",
    val detailedForecast: List<DetailedForecast> = emptyList()
)
