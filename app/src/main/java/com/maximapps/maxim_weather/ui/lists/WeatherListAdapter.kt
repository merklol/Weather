package com.maximapps.maxim_weather.ui.lists

import com.maximapps.maxim_weather.core.listAdapterOf
import com.maximapps.maxim_weather.domain.models.DetailedForecast
import com.maximapps.maxim_weather.ui.lists.viewholders.DetailedForecastViewHolder
import com.maximapps.maxim_weather.ui.lists.viewholders.TodayForecastViewHolder

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
