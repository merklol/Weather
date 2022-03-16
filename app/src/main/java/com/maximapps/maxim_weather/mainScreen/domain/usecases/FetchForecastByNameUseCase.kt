package com.maximapps.maxim_weather.mainScreen.domain.usecases

import com.maximapps.maxim_weather.mainScreen.domain.repositories.WeatherRepository
import javax.inject.Inject

class FetchForecastByNameUseCase @Inject constructor(private val repository: WeatherRepository) {
    operator fun invoke(cityName: String) = repository.fetchForecastByName(cityName)
}
