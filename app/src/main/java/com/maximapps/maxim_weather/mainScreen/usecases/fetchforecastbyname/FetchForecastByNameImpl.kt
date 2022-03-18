package com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbyname

import com.maximapps.maxim_weather.common.SingleUseCase
import com.maximapps.maxim_weather.mainScreen.usecases.models.WeatherData
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

typealias FetchForecastByName = SingleUseCase<FetchForecastByNameImpl.Params, WeatherData>

class FetchForecastByNameImpl @Inject constructor(
    private val cityWeatherRepository: CityWeatherRepository
) : FetchForecastByName {
    override operator fun invoke(params: Params?): Single<WeatherData> = when (params) {
        null -> Single.error(IllegalArgumentException("Parameter city name can't be null."))
        else -> cityWeatherRepository.fetchForecastByName(params.cityName)
    }

    data class Params(
        val cityName: String
    )
}
