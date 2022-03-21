package com.maximapps.maxim_weather.mainScreen.repositories.weather

import com.maximapps.maxim_weather.mainScreen.repositories.weather.network.WeatherService
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates.Coordinates
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates.LocationWeatherRepository
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbyname.CityWeatherRepository
import com.maximapps.maxim_weather.mainScreen.usecases.common.WeatherData
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Default implementation of the [LocationWeatherRepository], [CityWeatherRepository] interfaces.
 */
class WeatherRepository @Inject constructor(
    private val service: WeatherService,
    private val mapper: ResponseMapper
) : LocationWeatherRepository, CityWeatherRepository {
    override fun fetchWeatherForecast(coordinates: Coordinates): Single<WeatherData> = service
        .fetchWeatherForecast(coordinates.latitude, coordinates.longitude)
        .map(mapper::map)
        .subscribeOn(Schedulers.io())

    override fun fetchWeatherForecast(cityName: String): Single<WeatherData> = service
        .fetchWeatherForecast(cityName)
        .map(mapper::map)
        .subscribeOn(Schedulers.io())
}
