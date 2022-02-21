package com.maximapps.maxim_weather.ui.lists.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.core.ListAdapter
import com.maximapps.maxim_weather.core.listAdapterOf
import com.maximapps.maxim_weather.databinding.ListItemViewDetailedForecastBinding
import com.maximapps.maxim_weather.domain.models.Forecast
import com.maximapps.maxim_weather.ext.getString
import com.maximapps.maxim_weather.ext.toFormattedDate

/**
 * View holder for forecast card list item view.
 */
class ForecastViewHolder(
    private val binding: ListItemViewDetailedForecastBinding,
    private val onItemClick: (forecast: Forecast) -> Unit
) : ListAdapter.ViewHolder<Forecast>(binding) {
    private val adapter = listAdapterOf({ viewGroup, _ -> DetailedForecastViewHolder(viewGroup) })

    companion object {
        operator fun invoke(parent: ViewGroup, onItemClick: (forecast: Forecast) -> Unit) =
            ForecastViewHolder(
                ListItemViewDetailedForecastBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), onItemClick
            )
    }

    override fun bind(item: Forecast) {
        binding.date.text = item.date.toFormattedDate()
        binding.root.setOnClickListener { onItemClick(item) }
        binding.groupView.adapter = adapter.also { it.setData(item.detailedForecast) }
        binding.minTemperature.text = getString(R.string.temperature, item.temperatureMin)
        binding.maxTemperature.text = getString(R.string.temperature, item.temperatureMax)
    }
}
