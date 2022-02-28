package com.maximapps.maxim_weather.mainScreen.ui.adapter

import com.maximapps.maxim_weather.common.listAdapterOf
import com.maximapps.maxim_weather.mainScreen.domain.models.DetailedForecast

/**
 * Weather list view types.
 */
object ViewTypes {
    const val TodayForecast = 0
    const val DetailedForecast = 1
}

/**
 * Creates a ListAdapter for the weather forecast list.
 */
fun weatherListAdapter(onItemClick: (forecast: DetailedForecast) -> Unit) = listAdapterOf(
    init = { viewGroup, viewType ->
        when (viewType) {
            ViewTypes.TodayForecast -> TodayForecastViewHolder(viewGroup)
            else -> DetailedForecastViewHolder(viewGroup, onItemClick)
        }
    }, itemViewType = { if (it == ViewTypes.TodayForecast) 0 else ViewTypes.DetailedForecast }
)
