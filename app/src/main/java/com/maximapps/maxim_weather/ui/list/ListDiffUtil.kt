package com.maximapps.maxim_weather.ui.list

import androidx.recyclerview.widget.DiffUtil
import com.maximapps.maxim_weather.network.models.Forecast

class ListDiffUtil(
    //TODO: Change the Forecast model to the ui model
    private val old: List<Forecast>, private val new: List<Forecast>

): DiffUtil.Callback() {
    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        old[oldItemPosition].dt == new[newItemPosition].dt

    //TODO: Find a way how to compare the contents are better
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        old[oldItemPosition].main == new[newItemPosition].main
}