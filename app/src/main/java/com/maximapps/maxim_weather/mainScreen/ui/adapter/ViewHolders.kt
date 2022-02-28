package com.maximapps.maxim_weather.mainScreen.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.common.ListAdapter
import com.maximapps.maxim_weather.common.listAdapterOf
import com.maximapps.maxim_weather.common.utils.getString
import com.maximapps.maxim_weather.common.utils.toFormattedDate
import com.maximapps.maxim_weather.common.utils.toFormattedTime
import com.maximapps.maxim_weather.databinding.ListItemViewDetailedForecastBinding
import com.maximapps.maxim_weather.databinding.ListItemViewForecastBinding
import com.maximapps.maxim_weather.databinding.ListItemViewTodayForecastBinding
import com.maximapps.maxim_weather.mainScreen.domain.models.DetailedForecast
import com.maximapps.maxim_weather.mainScreen.domain.models.Forecast

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
        binding.details.text =
            getString(R.string.forecast_details, item.weatherCondition, item.feelsLike)
    }
}

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
