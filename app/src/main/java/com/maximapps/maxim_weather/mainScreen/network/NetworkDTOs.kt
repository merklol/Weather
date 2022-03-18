package com.maximapps.maxim_weather.mainScreen.network

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
    val city: CityEntity,
    @SerializedName("list")
    val forecastList: List<ForecastEntity>
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
data class CityEntity(
    val id: Int,
    val name: String,
    @SerializedName("coord")
    val coordinates: CoordinatesEntity,
    val country: String,
    val timezone: Int,
    val sunrise: Int,
    val sunset: Int
)

/**
 * City geo location.
 */
data class CoordinatesEntity(val lat: Double, val lon: Double)

/**
 * Forecast data
 */
data class ForecastEntity(
    val dt: Long,
    val main: MainEntity,
    val weather: List<WeatherEntity>,
    val clouds: CloudsEntity,
    val wind: WindEntity,
    val visibility: Int,
    val pop: Double,
    val rain: RainEntity?,
    val sys: SysEntity,
    @SerializedName("dt_txt")
    val dtTxt: String
)

/**
 * Main weather information
 */
data class MainEntity(
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
data class WeatherEntity(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

/**
 * Cloudiness in percent.
 */
data class CloudsEntity(val all: Int)

/**
 * Rain volume for last 3 hours.
 */
data class RainEntity(
    @SerializedName("3h")
    val threeHour: Double
)

/**
 * Wind speed, direction in degree and gust.
 */
data class WindEntity(val speed: Double, val deg: Int, val gust: Double)

/**
 *  Part of the day (n - night, d - day)
 */
data class SysEntity(val pod: String)
