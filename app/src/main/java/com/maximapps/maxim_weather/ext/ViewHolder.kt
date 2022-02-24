package com.maximapps.maxim_weather.ext

import androidx.recyclerview.widget.RecyclerView

/**
 * Returns a localized string from the string table.
 */
fun RecyclerView.ViewHolder.getString(resId: Int, vararg formatArgs: Any) =
    itemView.context.getString(resId, *formatArgs)
