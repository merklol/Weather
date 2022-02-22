package com.maximapps.maxim_weather.data.mappers

import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.core.Mapper
import com.maximapps.maxim_weather.data.network.models.Response
import com.maximapps.maxim_weather.domain.models.DetailedForecast
import com.maximapps.maxim_weather.domain.models.Undefined
import com.maximapps.maxim_weather.domain.models.WeatherData
import com.maximapps.maxim_weather.ext.capitalized
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.math.roundToInt

/**
 * Map a [Response] to a [WeatherData] instance from the Domain layer.
 */
class ResponseMapper @Inject constructor(
    private val iconMapper: IconMapper,
    private val forecastMapper: ForecastMapper
) : Mapper<Response, WeatherData> {

    override fun map(input: Response) = WeatherData(
        cityName = input.city.name,
        detailedForecast = input.groupByDate().map { item ->
            with(item.value.last()) {
                val weatherData = if (weather.isNotEmpty()) weather.last() else null
                DetailedForecast(
                    date = Date(TimeUnit.SECONDS.toMillis(dt)),
                    temperature = main.temp.roundToInt(),
                    temperatureMin = item.value.minOf { it.main.tempMin }.roundToInt(),
                    temperatureMax = item.value.maxOf { it.main.tempMax }.roundToInt(),
                    wind = wind.speed.roundToInt(),
                    feelsLike = main.feelsLike.roundToInt(),
                    weatherCondition = weatherData?.description?.capitalized ?: Undefined,
                    iconResId = weatherData?.let { iconMapper.map(it.icon) } ?: R.mipmap.few_clouds,
                    details = item.value.map { forecast -> forecastMapper.map(forecast) }
                )
            }
        }
    )
}
