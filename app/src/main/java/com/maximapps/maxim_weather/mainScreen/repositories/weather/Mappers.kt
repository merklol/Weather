package com.maximapps.maxim_weather.mainScreen.repositories.weatherrepository

import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.common.IconTypes
import com.maximapps.maxim_weather.common.Mapper
import com.maximapps.maxim_weather.common.utils.capitalized
import com.maximapps.maxim_weather.mainScreen.repositories.weatherrepository.network.ForecastEntity
import com.maximapps.maxim_weather.mainScreen.repositories.weatherrepository.network.Response
import com.maximapps.maxim_weather.mainScreen.usecases.models.DetailedForecast
import com.maximapps.maxim_weather.mainScreen.usecases.models.Undefined
import com.maximapps.maxim_weather.mainScreen.usecases.models.WeatherData
import com.maximapps.maxim_weather.mainScreen.usecases.models.WeatherForecast
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
                    weatherIcon = weatherData?.let { iconMapper.map(it.icon) }
                        ?: R.mipmap.few_clouds,
                    forecastList = item.value.map { forecast -> forecastMapper.map(forecast) }
                )
            }
        }
    )
}

/**
 * Map a [ForecastEntity] to a [WeatherForecast] instance from the Domain layer.
 */
class ForecastMapper @Inject constructor(
    private val mapper: IconMapper
) : Mapper<ForecastEntity, WeatherForecast> {
    override fun map(input: ForecastEntity) = with(input) {
        WeatherForecast(
            Date(TimeUnit.SECONDS.toMillis(dt)),
            main.temp.roundToInt(),
            main.tempMin.roundToInt(),
            main.tempMax.roundToInt(),
            mapper.map(if (weather.isNotEmpty()) weather.first().icon else IconTypes.Day.FewClouds)
        )
    }
}

/**
 * Map [IconTypes] to mipmap resources.
 */
class IconMapper @Inject constructor() : Mapper<String, Int> {
    override fun map(input: String) = when (input) {
        IconTypes.Day.ClearSky, IconTypes.Night.ClearSky -> R.mipmap.few_clouds
        IconTypes.Day.FewClouds, IconTypes.Night.FewClouds -> R.mipmap.few_clouds
        IconTypes.Day.ScatteredClouds, IconTypes.Night.ScatteredClouds -> R.mipmap.few_clouds
        IconTypes.Day.BrokenClouds, IconTypes.Night.BrokenClouds -> R.mipmap.few_clouds
        IconTypes.Day.ShowerRain, IconTypes.Night.ShowerRain -> R.mipmap.rain
        IconTypes.Day.Rain, IconTypes.Night.Rain -> R.mipmap.rain
        IconTypes.Day.Thunderstorm, IconTypes.Night.Thunderstorm -> R.mipmap.thunder
        IconTypes.Day.Snow, IconTypes.Night.Snow -> R.mipmap.few_clouds
        IconTypes.Day.Mist, IconTypes.Night.Mist -> R.mipmap.few_clouds
        else -> R.mipmap.few_clouds
    }
}
