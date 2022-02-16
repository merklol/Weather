package com.maximapps.maxim_weather.ui.list.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maximapps.maxim_weather.core.ListAdapter
import com.maximapps.maxim_weather.databinding.GroupItemViewBinding
import com.maximapps.maxim_weather.network.models.Forecast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

class GroupViewHolder(private val binding: GroupItemViewBinding) :
    ListAdapter.ViewHolder<Forecast>(binding) {

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

    override fun bind(item: Forecast) {
        val formatter = SimpleDateFormat("hh:mm", Locale.getDefault())
        binding.time.text = formatter.format(Date(item.dt * 1000))
        binding.temperature.text = item.main.temp.roundToInt().toString()
    }
}