package com.maximapps.weather.mainscreen.usecases.fetchforecastbycoordinates

import com.maximapps.weather.mainscreen.usecases.common.WeatherData
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining a method for fetching weather forecast data with 3-hour step.
 *
 * @see <a href="https://openweathermap.org/forecast5#5days">5 day / 3 hour forecast data</a>
 */
interface LocationWeatherRepository {
    /**
     * Fetch weather forecast data with 3-hour step by device geographic coordinates
     */
    fun fetchWeatherForecast(coordinates: Coordinates): Flow<WeatherData>
}
