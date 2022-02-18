package com.maximapps.maxim_weather.ext

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toFormattedDate(pattern: String = "dd MMMM, EEE"): String =
    SimpleDateFormat(pattern, Locale.getDefault()).format(this)

fun Date.toFormattedTime(pattern: String = "HH:mm"): String =
    SimpleDateFormat(pattern, Locale.getDefault()).format(this)