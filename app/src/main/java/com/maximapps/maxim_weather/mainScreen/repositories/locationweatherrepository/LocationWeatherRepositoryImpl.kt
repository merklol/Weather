package com.maximapps.maxim_weather.mainScreen.repositories.locationweatherrepository

import com.maximapps.maxim_weather.mainScreen.ResponseMapper
import com.maximapps.maxim_weather.mainScreen.network.WeatherService
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates.Coordinates
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates.LocationWeatherRepository
import com.maximapps.maxim_weather.mainScreen.usecases.models.WeatherData
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Default implementation of the [LocationWeatherRepository] interface.
 */
class LocationWeatherRepositoryImpl @Inject constructor(
    private val service: WeatherService,
    private val mapper: ResponseMapper,
) : LocationWeatherRepository {

    override fun fetchForecastByCoordinates(coordinates: Coordinates): Single<WeatherData> = service
        .fetchForecastByCoordinates(coordinates.latitude, coordinates.longitude)
        .map(mapper::map)
        .subscribeOn(Schedulers.io())
}
