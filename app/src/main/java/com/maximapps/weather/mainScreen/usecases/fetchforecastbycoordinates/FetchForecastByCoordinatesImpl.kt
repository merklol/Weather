package com.maximapps.weather.mainScreen.usecases.fetchforecastbycoordinates

import androidx.annotation.RequiresPermission
import com.maximapps.weather.common.FlowUseCase
import com.maximapps.weather.mainScreen.usecases.common.WeatherData
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

typealias FetchForecastByCoordinates = FlowUseCase<Nothing, WeatherData>

const val FETCH_FORECAST_BY_COORDINATES = "fetch_forecast_by_coordinates"

@OptIn(FlowPreview::class)
class FetchForecastByCoordinatesImpl @Inject constructor(
    private val locationRepository: LocationRepository,
    private val locationWeatherRepository: LocationWeatherRepository
) : FetchForecastByCoordinates {

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    override operator fun invoke(params: Nothing?): Flow<WeatherData> = locationRepository
        .getLocation()
        .flatMapConcat(locationWeatherRepository::fetchWeatherForecast)
}
