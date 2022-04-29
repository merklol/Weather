package com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbyname

import com.maximapps.maxim_weather.common.FlowUseCase
import com.maximapps.maxim_weather.mainScreen.usecases.common.WeatherData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias FetchForecastByName = FlowUseCase<FetchForecastByNameImpl.Params, WeatherData>

const val FETCH_FORECAST_BY_NAME = "fetch_forecast_by_name"

class FetchForecastByNameImpl @Inject constructor(
    private val cityWeatherRepository: CityWeatherRepository
) : FetchForecastByName {

    override operator fun invoke(params: Params?): Flow<WeatherData> = when (params) {
        null -> throw IllegalArgumentException("Parameter city name can't be null.")
        else -> cityWeatherRepository.fetchWeatherForecast(params.cityName)
    }

    data class Params(
        val cityName: String
    )
}
