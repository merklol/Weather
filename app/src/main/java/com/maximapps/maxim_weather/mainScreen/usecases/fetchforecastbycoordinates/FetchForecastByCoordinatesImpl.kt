package com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates

import androidx.annotation.RequiresPermission
import com.maximapps.maxim_weather.common.SingleUseCase
import com.maximapps.maxim_weather.mainScreen.usecases.common.WeatherData
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

typealias FetchForecastByCoordinates = SingleUseCase<Nothing, WeatherData>

const val FETCH_FORECAST_BY_COORDINATES = "fetch_forecast_by_coordinates"

class FetchForecastByCoordinatesImpl @Inject constructor(
    private val locationRepository: LocationRepository,
    private val locationWeatherRepository: LocationWeatherRepository
) : FetchForecastByCoordinates {

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    override operator fun invoke(params: Nothing?): Single<WeatherData> = locationRepository
        .getLocation()
        .flatMap(locationWeatherRepository::fetchWeatherForecast)
}
