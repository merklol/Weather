package com.maximapps.weather.mainScreen.usecases.fetchforecastbyname

import com.maximapps.weather.mainScreen.usecases.common.WeatherData
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining a method for fetching weather forecast data with 3-hour step.
 *
 * @see <a href="https://openweathermap.org/forecast5#5days">5 day / 3 hour forecast data</a>
 */
interface CityWeatherRepository {
    /**
     * Fetch weather forecast data with 3-hour step
     */
    fun fetchWeatherForecast(cityName: String): Flow<WeatherData>
}
