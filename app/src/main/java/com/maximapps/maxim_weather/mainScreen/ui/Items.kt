package com.maximapps.maxim_weather.mainScreen.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.common.utils.toFormattedDate
import com.maximapps.maxim_weather.common.utils.toFormattedTime
import com.maximapps.maxim_weather.databinding.ListItemViewDetailedForecastBinding
import com.maximapps.maxim_weather.databinding.ListItemViewTemperatureBinding
import com.maximapps.maxim_weather.databinding.ListItemViewTodayForecastBinding
import com.maximapps.maxim_weather.mainScreen.domain.models.DetailedForecast
import com.maximapps.maxim_weather.mainScreen.domain.models.Temperature
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil

/**
 * Item for temperature card list item view.
 */
class TemperatureItem(private val temperature: Temperature) :
    AbstractBindingItem<ListItemViewTemperatureBinding>() {
    override val type: Int
        get() = R.id.temperatureCardView

    @Suppress("unused_parameter")
    override var identifier: Long
        get() = temperature.hashCode().toLong()
        set(value) {}

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = ListItemViewTemperatureBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ListItemViewTemperatureBinding, payloads: List<Any>) {
        with(binding.root.resources) {
            binding.time.text = temperature.date.toFormattedTime()
            binding.weatherIcon.setImageResource(temperature.iconResId)
            binding.temperature.text = getString(R.string.temperature, temperature.temperature)
        }
    }
}

/**
 * Item for Today weather forecast card list item view.
 */
class TodayForecastItem(private val item: DetailedForecast) :
    AbstractBindingItem<ListItemViewTodayForecastBinding>() {
    override val type: Int
        get() = R.id.todayForecastCardView

    @Suppress("unused_parameter")
    override var identifier: Long
        get() = item.hashCode().toLong()
        set(value) {}

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = ListItemViewTodayForecastBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ListItemViewTodayForecastBinding, payloads: List<Any>) {
        with(binding.root.resources) {
            binding.weatherIcon.setImageResource(item.iconResId)
            binding.temperature.text = getString(R.string.temperature, item.temperature)
            binding.date.text = getString(R.string.current_date, item.date.toFormattedDate())
            binding.details.text = getString(
                R.string.forecast_details, item.weatherCondition, item.feelsLike
            )
        }
    }
}

/**
 * Item for detailed forecast card list item view.
 */
class DetailedForecastItem(private val item: DetailedForecast) :
    AbstractBindingItem<ListItemViewDetailedForecastBinding>() {
    private val adapter = ItemAdapter<TemperatureItem>()
    private val fastAdapter = FastAdapter.with(adapter)

    override val type: Int
        get() = R.id.detailedForecastCardView

    @Suppress("unused_parameter")
    override var identifier: Long
        get() = item.hashCode().toLong()
        set(value) {}

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = ListItemViewDetailedForecastBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ListItemViewDetailedForecastBinding, payloads: List<Any>) {
        FastAdapterDiffUtil[adapter] = item.temperatureList.map { TemperatureItem(it) }
        with(binding.root.resources) {
            binding.date.text = item.date.toFormattedDate()
            binding.detailList.adapter = fastAdapter
            binding.minTemperature.text = getString(R.string.temperature, item.temperatureMin)
            binding.maxTemperature.text = getString(R.string.temperature, item.temperatureMax)
        }
    }
}
