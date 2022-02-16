package com.maximapps.maxim_weather.ui.list.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maximapps.maxim_weather.core.ListAdapter
import com.maximapps.maxim_weather.core.listAdapterOf
import com.maximapps.maxim_weather.databinding.ListItemViewBinding
import com.maximapps.maxim_weather.network.models.Forecast
import kotlin.math.roundToInt

class ListViewHolder(
    private val binding: ListItemViewBinding,
    private val onItemClick: () -> Unit
) : ListAdapter.ViewHolder<Pair<String, List<Forecast>>>(binding) {
    private val adapter = listAdapterOf({ viewGroup, _ -> GroupViewHolder(viewGroup) })

    companion object {
        operator fun invoke(parent: ViewGroup, onItemClick: () -> Unit) =
            ListViewHolder(
                ListItemViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), onItemClick
            )
    }

    override fun bind(item: Pair<String, List<Forecast>>) {
        binding.groupView.adapter = adapter
        adapter.setData(item.second)
        itemView.setOnClickListener { onItemClick() }
        binding.date.text = item.first
        binding.minTemperature.text = item.second[0].main.tempMin.roundToInt().toString()
        binding.maxTemperature.text = item.second[0].main.tempMax.roundToInt().toString()
    }
}