package com.maximapps.maxim_weather.mainScreen.domain.usecases

import androidx.annotation.RequiresPermission
import com.maximapps.maxim_weather.mainScreen.domain.repositories.LocationRepository
import com.maximapps.maxim_weather.mainScreen.domain.repositories.WeatherRepository
import com.maximapps.maxim_weather.mainScreen.domain.models.WeatherData
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FetchForecastByCoordinatesUseCase @Inject constructor(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository
) {
    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    operator fun invoke(): Single<WeatherData> = locationRepository
        .getLocation()
        .flatMap(weatherRepository::fetchForecastByCoordinates)
}
