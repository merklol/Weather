package com.maximapps.maxim_weather.domain

import com.maximapps.maxim_weather.domain.models.CityWeather
import io.reactivex.rxjava3.core.Single

interface WeatherRepository {
    fun getForecast(cityName: String): Single<CityWeather>
}