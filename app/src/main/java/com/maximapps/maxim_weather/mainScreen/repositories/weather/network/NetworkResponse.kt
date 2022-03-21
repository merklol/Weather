package com.maximapps.maxim_weather.mainScreen.repositories.weather.network

import com.google.gson.annotations.SerializedName
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit

/**
 * Response from OpenWeather API with 5 day weather forecast
 */
data class Response(
    val cod: String,
    val message: String,
    val city: City,
    @SerializedName("list")
    val forecastList: List<Forecast>
) {
    fun groupByDate() = forecastList.groupBy {
        with(Calendar.getInstance()) {
            time = Date(TimeUnit.SECONDS.toMillis(it.dt))
            get(Calendar.DATE)
        }
    }
}

/**
 * City information
 */
data class City(
    val id: Int,
    val name: String,
    @SerializedName("coord")
    val coordinates: Coordinates,
    val country: String,
    val timezone: Int,
    val sunrise: Int,
    val sunset: Int
)

/**
 * City geo location.
 */
data class Coordinates(val lat: Double, val lon: Double)

/**
 * Forecast data
 */
data class Forecast(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    val pop: Double,
    val rain: Rain?,
    val sys: Sys,
    @SerializedName("dt_txt")
    val dtTxt: String
)

/**
 * Main weather information
 */
data class Main(
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    val pressure: Int,
    @SerializedName("sea_level")
    val seaLevel: Int,
    @SerializedName("grnd_level")
    val groundLevel: Int,
    val humidity: Int,
    @SerializedName("temp_kf")
    val tempKf: Double
)

/**
 * Weather condition
 */
data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

/**
 * Cloudiness in percent.
 */
data class Clouds(val all: Int)

/**
 * Rain volume for last 3 hours.
 */
data class Rain(
    @SerializedName("3h")
    val threeHour: Double
)

/**
 * Wind speed, direction in degree and gust.
 */
data class Wind(val speed: Double, val deg: Int, val gust: Double)

/**
 *  Part of the day (n - night, d - day)
 */
data class Sys(val pod: String)
