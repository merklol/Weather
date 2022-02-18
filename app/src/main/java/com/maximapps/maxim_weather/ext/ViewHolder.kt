package com.maximapps.maxim_weather.ext

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.ViewHolder.getString(resId: Int, vararg formatArgs: Any) =
    itemView.context.getString(resId, *formatArgs)
