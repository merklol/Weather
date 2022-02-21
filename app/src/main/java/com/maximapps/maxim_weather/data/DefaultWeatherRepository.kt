package com.maximapps.maxim_weather.data

import com.maximapps.maxim_weather.data.mappers.ResponseMapper
import com.maximapps.maxim_weather.domain.WeatherRepository
import com.maximapps.maxim_weather.domain.models.WeatherData
import com.maximapps.maxim_weather.data.network.WeatherService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Default implementation of the [WeatherRepository] interface.
 */
class DefaultWeatherRepository @Inject constructor(
    private val service: WeatherService,
    private val mapper: ResponseMapper
) : WeatherRepository {

    override fun getForecast(cityName: String): Single<WeatherData> {
        return service.getForecast(cityName).subscribeOn(Schedulers.io()).map { mapper.map(it) }
    }
}
