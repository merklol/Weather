package com.maximapps.maxim_weather.ui.list

import androidx.recyclerview.widget.DiffUtil
import com.maximapps.maxim_weather.data.network.models.ForecastEntity

class ListDiffUtil(
    //TODO: Change the Forecast model to the ui model
    private val old: List<ForecastEntity>, private val new: List<ForecastEntity>

): DiffUtil.Callback() {
    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        old[oldItemPosition].dt == new[newItemPosition].dt

    //TODO: Find a way how to compare the contents are better
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        old[oldItemPosition].main == new[newItemPosition].main
}