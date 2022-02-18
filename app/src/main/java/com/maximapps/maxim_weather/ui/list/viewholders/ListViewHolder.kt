package com.maximapps.maxim_weather.ui.list.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.core.ListAdapter
import com.maximapps.maxim_weather.core.listAdapterOf
import com.maximapps.maxim_weather.databinding.ListItemViewBinding
import com.maximapps.maxim_weather.domain.models.Forecast
import com.maximapps.maxim_weather.ext.getString
import com.maximapps.maxim_weather.ext.toFormattedDate

class ListViewHolder(
    private val binding: ListItemViewBinding,
    private val onItemClick: (forecast: Forecast) -> Unit
) : ListAdapter.ViewHolder<Forecast>(binding) {
    private val adapter = listAdapterOf({ viewGroup, _ -> GroupViewHolder(viewGroup) })

    companion object {
        operator fun invoke(parent: ViewGroup, onItemClick: (forecast: Forecast) -> Unit) =
            ListViewHolder(
                ListItemViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), onItemClick
            )
    }

    override fun bind(item: Forecast) {
        itemView.setOnClickListener { onItemClick(item) }
        binding.date.text = item.date.toFormattedDate()
        binding.groupView.adapter = adapter.also { it.setData(item.details) }
        binding.minTemperature.text = getString(R.string.temperature, item.temperatureMin)
        binding.maxTemperature.text = getString(R.string.temperature, item.temperatureMax)
    }
}
