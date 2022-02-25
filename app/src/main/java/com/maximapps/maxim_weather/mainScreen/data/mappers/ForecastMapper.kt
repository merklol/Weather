package com.maximapps.maxim_weather.mainScreen.data.mappers

import com.maximapps.maxim_weather.common.mappers.Mapper
import com.maximapps.maxim_weather.mainScreen.data.network.IconTypes
import com.maximapps.maxim_weather.mainScreen.data.network.models.ForecastEntity
import com.maximapps.maxim_weather.mainScreen.domain.models.Forecast
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.math.roundToInt

/**
 * Map a [ForecastEntity] to a [Forecast] instance from the Domain layer.
 */
class ForecastMapper @Inject constructor(
    private val mapper: IconMapper
) : Mapper<ForecastEntity, Forecast> {
    override fun map(input: ForecastEntity) = with(input) {
        Forecast(
            Date(TimeUnit.SECONDS.toMillis(dt)),
            main.temp.roundToInt(),
            main.tempMin.roundToInt(),
            main.tempMax.roundToInt(),
            mapper.map(if (weather.isNotEmpty()) weather.first().icon else IconTypes.Day.FewClouds)
        )
    }
}
