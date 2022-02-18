package com.maximapps.maxim_weather.data.mappers

import com.maximapps.maxim_weather.core.Mapper
import com.maximapps.maxim_weather.domain.models.DetailedForecast
import com.maximapps.maxim_weather.data.network.models.ForecastEntity
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.math.roundToInt

class DetailedForecastMapper @Inject constructor(
    private val mapper: IconMapper
) : Mapper<ForecastEntity, DetailedForecast> {
    override fun map(input: ForecastEntity) = with(input) {
        DetailedForecast(
            Date(TimeUnit.SECONDS.toMillis(dt)),
            main.temp.roundToInt(),
            main.tempMin.roundToInt(),
            main.tempMax.roundToInt(),
            mapper.map(weather.first().icon)
        )
    }
}
