package com.maximapps.maxim_weather.data.mappers

import com.maximapps.maxim_weather.core.Mapper
import com.maximapps.maxim_weather.data.network.models.Response
import com.maximapps.maxim_weather.domain.models.CityWeather
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.math.roundToInt

class ForecastMapper @Inject constructor(
    private val iconMapper: IconMapper,
    private val forecastMapper: DetailedForecastMapper
) : Mapper<Response, CityWeather> {

    override fun map(input: Response) = CityWeather(
        cityName = input.city.name,
        forecastList = groupDetailedForecastByDate(input).map { item ->
            with(item.value.last()) {
                com.maximapps.maxim_weather.domain.models.Forecast(
                    date = Date(TimeUnit.SECONDS.toMillis(dt)),
                    temperature = main.temp.roundToInt(),
                    temperatureMin = item.value.minOf { it.main.tempMin }.roundToInt(),
                    temperatureMax = item.value.maxOf { it.main.tempMax }.roundToInt(),
                    wind = wind.speed.roundToInt(),
                    feelsLike = main.feelsLike.roundToInt(),
                    weather = weather.first().main,
                    iconResId = iconMapper.map(weather.first().icon),
                    details = item.value.drop(1).map { forecast -> forecastMapper.map(forecast) }
                )
            }
        }
    )

    private fun groupDetailedForecastByDate(response: Response) = response.forecastList
        .groupBy {
            with(GregorianCalendar.getInstance()) {
                time = Date(TimeUnit.SECONDS.toMillis(it.dt))
                get(Calendar.DATE)
            }
        }
}
