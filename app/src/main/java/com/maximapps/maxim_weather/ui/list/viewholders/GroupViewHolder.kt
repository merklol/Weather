package com.maximapps.maxim_weather.ui.list.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.core.ListAdapter
import com.maximapps.maxim_weather.databinding.GroupItemViewBinding
import com.maximapps.maxim_weather.domain.models.DetailedForecast
import com.maximapps.maxim_weather.ext.getString
import com.maximapps.maxim_weather.ext.toFormattedTime

class GroupViewHolder(private val binding: GroupItemViewBinding) :
    ListAdapter.ViewHolder<DetailedForecast>(binding) {

    companion object {
        operator fun invoke(parent: ViewGroup) =
            GroupViewHolder(
                GroupItemViewBinding.inflate(
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
