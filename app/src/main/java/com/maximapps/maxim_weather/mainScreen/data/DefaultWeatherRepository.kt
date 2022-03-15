package com.maximapps.maxim_weather.mainScreen.data

import androidx.annotation.RequiresPermission
import com.maximapps.maxim_weather.mainScreen.data.lcation.Coordinates
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

    override fun fetchForecast(cityName: String): Single<WeatherData> = service
        .fetchForecast(cityName)
        .map(mapper::map)
        .subscribeOn(Schedulers.io())

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    override fun fetchForecastByLocation(): Single<WeatherData> = locationDataSource
        .getLocation()
        .flatMap(::fetchForecastByCoordinates)
        .map(mapper::map)
        .subscribeOn(Schedulers.io())

    private fun fetchForecastByCoordinates(coordinates: Coordinates) = service
        .fetchForecastByCoordinates(coordinates.latitude, coordinates.longitude)
        .subscribeOn(Schedulers.io())
}
