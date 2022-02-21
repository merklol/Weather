package com.maximapps.maxim_weather.data.mappers

import com.maximapps.maxim_weather.core.Mapper
import com.maximapps.maxim_weather.data.network.models.Response
import com.maximapps.maxim_weather.domain.models.DetailedForecast
import com.maximapps.maxim_weather.domain.models.WeatherData
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.math.roundToInt

/**
 * Map a [Response] to a [WeatherData] instance from the Domain layer.
 */
class WeatherDataMapper @Inject constructor(
    private val iconMapper: IconMapper,
    private val forecastMapper: ForecastMapper
) : Mapper<Response, WeatherData> {

    override fun map(input: Response) = WeatherData(
        cityName = input.city.name,
        detailedForecastList = groupDetailedForecastByDate(input).map { item ->
            with(item.value.last()) {
                DetailedForecast(
                    date = Date(TimeUnit.SECONDS.toMillis(dt)),
                    temperature = main.temp.roundToInt(),
                    temperatureMin = item.value.minOf { it.main.tempMin }.roundToInt(),
                    temperatureMax = item.value.maxOf { it.main.tempMax }.roundToInt(),
                    wind = wind.speed.roundToInt(),
                    feelsLike = main.feelsLike.roundToInt(),
                    weather = weather.first().main,
                    iconResId = iconMapper.map(weather.first().icon),
                    details = item.value.map { forecast -> forecastMapper.map(forecast) }
                )
            }
        }
    )

    private fun groupDetailedForecastByDate(response: Response) = response.forecastList
        .groupBy {
            with(Calendar.getInstance()) {
                time = Date(TimeUnit.SECONDS.toMillis(it.dt))
                get(Calendar.DATE)
            }
        }
}
