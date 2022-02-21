package com.maximapps.maxim_weather.ui.lists.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.core.ListAdapter
import com.maximapps.maxim_weather.core.listAdapterOf
import com.maximapps.maxim_weather.databinding.ListItemViewDetailedForecastBinding
import com.maximapps.maxim_weather.domain.models.DetailedForecast
import com.maximapps.maxim_weather.ext.getString
import com.maximapps.maxim_weather.ext.toFormattedDate

/**
 * View holder for detailed forecast card list item view.
 */
class DetailedForecastViewHolder(
    private val binding: ListItemViewDetailedForecastBinding,
    private val onItemClick: (forecast: DetailedForecast) -> Unit
) : ListAdapter.ViewHolder<DetailedForecast>(binding) {
    private val adapter = listAdapterOf({ viewGroup, _ -> ForecastViewHolder(viewGroup) })

    companion object {
        operator fun invoke(parent: ViewGroup, onItemClick: (forecast: DetailedForecast) -> Unit) =
            DetailedForecastViewHolder(
                ListItemViewDetailedForecastBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), onItemClick
            )
    }

    override fun bind(item: DetailedForecast) {
        binding.date.text = item.date.toFormattedDate()
        binding.root.setOnClickListener { onItemClick(item) }
        binding.detailList.adapter = adapter.also { it.setData(item.details) }
        binding.minTemperature.text = getString(R.string.temperature, item.temperatureMin)
        binding.maxTemperature.text = getString(R.string.temperature, item.temperatureMax)
    }
}
