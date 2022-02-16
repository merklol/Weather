package com.maximapps.maxim_weather.ui.list.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maximapps.maxim_weather.core.ListAdapter
import com.maximapps.maxim_weather.databinding.ListItemView2Binding
import com.maximapps.maxim_weather.network.models.Forecast
import kotlin.math.roundToInt

class TodayViewHolder(
    private val binding: ListItemView2Binding,
) : ListAdapter.ViewHolder<Pair<String, List<Forecast>>>(binding) {

    companion object {
        operator fun invoke(parent: ViewGroup) =
            TodayViewHolder(
                ListItemView2Binding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    override fun bind(item: Pair<String, List<Forecast>>) {
        binding.temperature.text = item.second[0].main.temp.roundToInt().toString()
    }
}