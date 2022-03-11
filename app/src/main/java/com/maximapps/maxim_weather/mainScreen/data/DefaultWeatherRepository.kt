package com.maximapps.maxim_weather.mainScreen.data

import com.maximapps.maxim_weather.mainScreen.data.lcation.LocationDataSource
import com.maximapps.maxim_weather.mainScreen.data.network.WeatherService
import com.maximapps.maxim_weather.mainScreen.domain.WeatherRepository
import com.maximapps.maxim_weather.mainScreen.domain.models.WeatherData
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Default implementation of the [WeatherRepository] interface.
 */
class DefaultWeatherRepository @Inject constructor(
    private val service: WeatherService,
    private val mapper: ResponseMapper,
    private val locationDataSource: LocationDataSource
) : WeatherRepository {

    override fun fetchForecast(cityName: String): Single<WeatherData> =
        service.fetchForecast(cityName).map { mapper.map(it) }.subscribeOn(Schedulers.io())

    override fun fetchForecastByLocation(): Single<WeatherData> =
        locationDataSource.getLocation().flatMap {
            service.fetchForecastByCoordinates(it.latitude, it.longitude)
                .subscribeOn(Schedulers.io())
        }.map { mapper.map(it) }.subscribeOn(Schedulers.io())
}
