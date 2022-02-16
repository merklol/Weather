package com.maximapps.maxim_weather.ui.list

import androidx.navigation.NavController
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.core.listAdapterOf
import com.maximapps.maxim_weather.ui.list.viewholders.ListViewHolder
import com.maximapps.maxim_weather.ui.list.viewholders.TodayViewHolder

fun weatherListAdapter(navController: NavController) = listAdapterOf(
    init = { viewGroup, viewType ->
        when (viewType) {
            ViewTypes.TodayView -> TodayViewHolder(viewGroup)
            else -> ListViewHolder(viewGroup) {
                navController.navigate(R.id.action_mainFragment_to_detailsFragment)
            }
        }
    }, itemViewType = { if (it == ViewTypes.TodayView) 0 else ViewTypes.GroupView }
)