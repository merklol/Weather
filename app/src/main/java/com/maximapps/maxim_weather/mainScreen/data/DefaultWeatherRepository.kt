package com.maximapps.maxim_weather.mainScreen.data

import com.maximapps.maxim_weather.mainScreen.data.network.WeatherService
import com.maximapps.maxim_weather.mainScreen.domain.WeatherRepository
import com.maximapps.maxim_weather.mainScreen.domain.models.WeatherData
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Default implementation of the [WeatherRepository] interface.
 */
class DefaultWeatherRepository @Inject constructor(
    private val service: WeatherService,
    private val mapper: ResponseMapper
) : WeatherRepository {

    override fun fetchForecast(cityName: String): Single<WeatherData> {
        return service.fetchForecast(cityName).map { mapper.map(it) }
    }
}
