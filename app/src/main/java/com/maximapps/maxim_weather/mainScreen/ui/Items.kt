package com.maximapps.maxim_weather.mainScreen.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.common.utils.toFormattedDate
import com.maximapps.maxim_weather.common.utils.toFormattedTime
import com.maximapps.maxim_weather.databinding.ListItemViewDetailedForecastBinding
import com.maximapps.maxim_weather.databinding.ListItemViewTodayForecastBinding
import com.maximapps.maxim_weather.databinding.ListItemViewWeatherForecastBinding
import com.maximapps.maxim_weather.mainScreen.usecases.models.DetailedForecast
import com.maximapps.maxim_weather.mainScreen.usecases.models.WeatherForecast
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil

/**
 * Item for weather forecast card list item view.
 */
class WeatherForecastItem(private val weatherForecast: WeatherForecast) :
    AbstractBindingItem<ListItemViewWeatherForecastBinding>() {
    override val type: Int
        get() = R.id.weatherForecastCardView

    @Suppress("unused_parameter")
    override var identifier: Long
        get() = weatherForecast.hashCode().toLong()
        set(value) {}

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = ListItemViewWeatherForecastBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ListItemViewWeatherForecastBinding, payloads: List<Any>) {
        with(binding.root.resources) {
            binding.time.text = weatherForecast.date.toFormattedTime()
            binding.weatherIcon.setImageResource(weatherForecast.weatherIcon)
            binding.temperature.text = getString(R.string.temperature, weatherForecast.temperature)
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
            binding.weatherIcon.setImageResource(item.weatherIcon)
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
    private val adapter = ItemAdapter<WeatherForecastItem>()
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
        FastAdapterDiffUtil[adapter] = item.forecastList.map { WeatherForecastItem(it) }
        with(binding.root.resources) {
            binding.date.text = item.date.toFormattedDate()
            binding.detailList.adapter = fastAdapter
            binding.minTemperature.text = getString(R.string.temperature, item.temperatureMin)
            binding.maxTemperature.text = getString(R.string.temperature, item.temperatureMax)
        }
    }
}
