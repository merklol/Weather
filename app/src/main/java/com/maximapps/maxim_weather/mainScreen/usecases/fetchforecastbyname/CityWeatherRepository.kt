package com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbyname

import com.maximapps.maxim_weather.mainScreen.usecases.common.WeatherData
import io.reactivex.rxjava3.core.Single

/**
 * Interface defining a method for fetching weather forecast data with 3-hour step.
 *
 * @see <a href="https://openweathermap.org/forecast5#5days">5 day / 3 hour forecast data</a>
 */
interface CityWeatherRepository {
    /**
     * Fetch weather forecast data with 3-hour step
     */
    fun fetchWeatherForecast(cityName: String): Single<WeatherData>
}
