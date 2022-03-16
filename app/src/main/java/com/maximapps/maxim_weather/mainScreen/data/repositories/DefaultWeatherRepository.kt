package com.maximapps.maxim_weather.mainScreen.data.repositories

import com.maximapps.maxim_weather.mainScreen.data.ResponseMapper
import com.maximapps.maxim_weather.mainScreen.data.network.WeatherService
import com.maximapps.maxim_weather.mainScreen.domain.models.Coordinates
import com.maximapps.maxim_weather.mainScreen.domain.models.WeatherData
import com.maximapps.maxim_weather.mainScreen.domain.repositories.WeatherRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Default implementation of the [WeatherRepository] interface.
 */
class DefaultWeatherRepository @Inject constructor(
    private val service: WeatherService,
    private val mapper: ResponseMapper,
) : WeatherRepository {

    override fun fetchForecastByName(cityName: String): Single<WeatherData> = service
        .fetchForecast(cityName)
        .map(mapper::map)
        .subscribeOn(Schedulers.io())

    override fun fetchForecastByCoordinates(coordinates: Coordinates): Single<WeatherData> = service
        .fetchForecastByCoordinates(coordinates.latitude, coordinates.longitude)
        .map(mapper::map)
        .subscribeOn(Schedulers.io())
}
