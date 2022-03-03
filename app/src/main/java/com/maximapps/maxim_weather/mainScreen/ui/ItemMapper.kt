package com.maximapps.maxim_weather.mainScreen.ui

import com.maximapps.maxim_weather.common.Mapper
import com.maximapps.maxim_weather.mainScreen.domain.models.DetailedForecast
import com.maximapps.maxim_weather.mainScreen.domain.models.WeatherData
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import javax.inject.Inject

/**
 * Map a [WeatherData] instance to a [AbstractBindingItem] instance of FastAdapter.
 */
class ItemMapper @Inject constructor() : Mapper<List<DetailedForecast>, List<GenericItem>> {
    override fun map(input: List<DetailedForecast>): List<GenericItem> =
        input.mapIndexed { index, detailedForecast ->
            when (index) {
                0 -> TodayForecastItem(detailedForecast)
                else -> DetailedForecastItem(detailedForecast)
            }
        }
}
