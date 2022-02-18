package com.maximapps.maxim_weather.ui.list

import com.maximapps.maxim_weather.core.listAdapterOf
import com.maximapps.maxim_weather.domain.models.Forecast
import com.maximapps.maxim_weather.ui.list.viewholders.ListViewHolder
import com.maximapps.maxim_weather.ui.list.viewholders.TodayViewHolder

fun weatherListAdapter(onItemClick: (forecast: Forecast) -> Unit) = listAdapterOf(
    init = { viewGroup, viewType ->
        when (viewType) {
            ViewTypes.TodayView -> TodayViewHolder(viewGroup)
            else -> ListViewHolder(viewGroup, onItemClick)
        }
    }, itemViewType = { if (it == ViewTypes.TodayView) 0 else ViewTypes.GroupView }
)