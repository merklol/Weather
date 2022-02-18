package com.maximapps.maxim_weather.data

import com.maximapps.maxim_weather.data.mappers.ForecastMapper
import com.maximapps.maxim_weather.domain.WeatherRepository
import com.maximapps.maxim_weather.domain.models.CityWeather
import com.maximapps.maxim_weather.data.network.WeatherService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DefaultWeatherRepository @Inject constructor(
    private val service: WeatherService,
    private val mapper: ForecastMapper
) : WeatherRepository {

    override fun getForecast(cityName: String): Single<CityWeather> {
        return service.getForecast(cityName).subscribeOn(Schedulers.io()).map { mapper.map(it) }
    }
}
