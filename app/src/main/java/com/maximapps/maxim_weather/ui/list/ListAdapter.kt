package com.maximapps.maxim_weather.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.maximapps.maxim_weather.databinding.WeatherListItemViewBinding
import com.maximapps.maxim_weather.network.models.Forecast

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    //Temp solution
    private val items = mutableListOf<Forecast>()


    fun setData(data: List<Forecast>) {
        val result = DiffUtil.calculateDiff(ListDiffUtil(items, data))
        items.clear()
        items.addAll(data)
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(
        WeatherListItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class ListViewHolder(private val binding: WeatherListItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //Temp solution
        @SuppressLint("SetTextI18n")
        fun bind(forecast: Forecast) {
            binding.date.text = forecast.dtTxt
            binding.rain.text = "Rain: ${forecast.rain?.threeHour?.toString() ?: ""}"
            binding.pressure.text = "Pressure: ${forecast.main.pressure}"
            binding.temperature.text = "Temperature: ${forecast.main.temp}"
            binding.wind.text = "Wind: ${forecast.wind.speed}"
        }
    }
}