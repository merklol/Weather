package com.maximapps.maxim_weather.ui.lists.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.common.ListAdapter
import com.maximapps.maxim_weather.databinding.ListItemViewForecastBinding
import com.maximapps.maxim_weather.domain.models.Forecast
import com.maximapps.maxim_weather.common.utils.getString
import com.maximapps.maxim_weather.common.utils.toFormattedTime

/**
 * View holder for forecast card list item view.
 */
class ForecastViewHolder(private val binding: ListItemViewForecastBinding) :
    ListAdapter.ViewHolder<Forecast>(binding) {

    companion object {
        operator fun invoke(parent: ViewGroup) =
            ForecastViewHolder(
                ListItemViewForecastBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    override fun bind(item: Forecast) {
        binding.time.text = item.date.toFormattedTime()
        binding.weatherIcon.setImageResource(item.iconResId)
        binding.temperature.text = getString(R.string.temperature, item.temperature)
    }
}
