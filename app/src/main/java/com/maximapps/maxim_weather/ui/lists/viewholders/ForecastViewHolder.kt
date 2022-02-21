package com.maximapps.maxim_weather.ui.lists.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.core.ListAdapter
import com.maximapps.maxim_weather.databinding.ListItemViewForecastBinding
import com.maximapps.maxim_weather.domain.models.DetailedForecast
import com.maximapps.maxim_weather.ext.getString
import com.maximapps.maxim_weather.ext.toFormattedTime

/**
 * View holder for detailed forecast card list item view.
 */
class DetailedForecastViewHolder(private val binding: ListItemViewForecastBinding) :
    ListAdapter.ViewHolder<DetailedForecast>(binding) {

    companion object {
        operator fun invoke(parent: ViewGroup) =
            DetailedForecastViewHolder(
                ListItemViewForecastBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    override fun bind(item: DetailedForecast) {
        binding.time.text = item.date.toFormattedTime()
        binding.weatherIcon.setImageResource(item.iconResId)
        binding.temperature.text = getString(R.string.temperature, item.temperature)
    }
}
