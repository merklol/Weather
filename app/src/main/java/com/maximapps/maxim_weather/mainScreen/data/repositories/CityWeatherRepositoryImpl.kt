package com.maximapps.maxim_weather.mainScreen.data.repositories

import com.maximapps.maxim_weather.mainScreen.data.ResponseMapper
import com.maximapps.maxim_weather.mainScreen.data.network.WeatherService
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbyname.CityWeatherRepository
import com.maximapps.maxim_weather.mainScreen.usecases.models.WeatherData
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Default implementation of the [CityWeatherRepository] interface.
 */
class DefaultCityWeatherRepository @Inject constructor(
    private val service: WeatherService,
    private val mapper: ResponseMapper,
) : CityWeatherRepository {

    override fun fetchForecastByName(cityName: String): Single<WeatherData> = service
        .fetchForecast(cityName)
        .map(mapper::map)
        .subscribeOn(Schedulers.io())
}
