package com.maximapps.maxim_weather.domain.models

data class CityWeather(val cityName: String, val forecastList: List<Forecast>)