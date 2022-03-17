package com.maximapps.maxim_weather.mainScreen.domain.usecases

import com.maximapps.maxim_weather.common.SingleUseCase
import com.maximapps.maxim_weather.mainScreen.domain.models.WeatherData
import com.maximapps.maxim_weather.mainScreen.domain.repositories.WeatherRepository
import io.reactivex.rxjava3.core.Single
import java.lang.IllegalArgumentException
import javax.inject.Inject

typealias FetchForecastByNameUseCase = SingleUseCase<FetchForecastByName.Params, WeatherData>

class FetchForecastByName @Inject constructor(
    private val repository: WeatherRepository
) : FetchForecastByNameUseCase {
    override operator fun invoke(params: Params?): Single<WeatherData> = when (params) {
        null -> Single.error(IllegalArgumentException("Parameter city name can't be null."))
        else -> repository.fetchForecastByName(params.cityName)
    }

    data class Params(
        val cityName: String
    )
}
