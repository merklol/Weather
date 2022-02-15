package com.maximapps.maxim_weather.ui.list

import androidx.recyclerview.widget.DiffUtil
import com.maximapps.maxim_weather.network.models.Forecast

class ListDiffUtil(
    //Temp solution
    private val old: List<Forecast>, private val new: List<Forecast>

): DiffUtil.Callback() {
    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        old[oldItemPosition].dt == new[newItemPosition].dt

    //Temp solution
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        old[oldItemPosition].main == new[newItemPosition].main
}