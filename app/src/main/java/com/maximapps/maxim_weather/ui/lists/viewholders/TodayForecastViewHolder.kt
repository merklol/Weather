package com.maximapps.maxim_weather.ui.lists.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.common.ListAdapter
import com.maximapps.maxim_weather.databinding.ListItemViewTodayForecastBinding
import com.maximapps.maxim_weather.domain.models.DetailedForecast
import com.maximapps.maxim_weather.common.utils.getString
import com.maximapps.maxim_weather.common.utils.toFormattedDate

/**
 * View holder for Today forecast card list item view.
 */
class TodayForecastViewHolder(
    private val binding: ListItemViewTodayForecastBinding,
) : ListAdapter.ViewHolder<DetailedForecast>(binding) {

    companion object {
        operator fun invoke(parent: ViewGroup) =
            TodayForecastViewHolder(
                ListItemViewTodayForecastBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    override fun bind(item: DetailedForecast) {
        binding.weatherIcon.setImageResource(item.iconResId)
        binding.temperature.text = getString(R.string.temperature, item.temperature)
        binding.date.text = getString(R.string.current_date, item.date.toFormattedDate())
        binding.details.text = getString(R.string.forecast_details, item.weatherCondition, item.feelsLike)
    }
}
