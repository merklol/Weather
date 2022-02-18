package com.maximapps.maxim_weather.ui.list.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.core.ListAdapter
import com.maximapps.maxim_weather.databinding.ListItemView2Binding
import com.maximapps.maxim_weather.domain.models.Forecast
import com.maximapps.maxim_weather.ext.getString
import com.maximapps.maxim_weather.ext.toFormattedDate

class TodayViewHolder(
    private val binding: ListItemView2Binding,
) : ListAdapter.ViewHolder<Forecast>(binding) {

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

    override fun bind(item: Forecast) {
        binding.weatherIcon.setImageResource(item.iconResId)
        binding.temperature.text = getString(R.string.temperature, item.temperature)
        binding.date.text = getString(R.string.today_date, item.date.toFormattedDate())
        binding.details.text = getString(R.string.today_details, item.weather, item.feelsLike)
    }
}
