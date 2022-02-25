package com.maximapps.maxim_weather.common.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Formats a Date into a string using the given pattern.
 *
 * @param pattern default pattern (dd MMMM, EEE).
 */
fun Date.toFormattedDate(pattern: String = "dd MMMM, EEE"): String =
    SimpleDateFormat(pattern, Locale.getDefault()).format(this)

/**
 * Formats a Date into a string using the given pattern.
 *
 * @param pattern default pattern (HH:mm).
 */
fun Date.toFormattedTime(pattern: String = "HH:mm"): String =
    SimpleDateFormat(pattern, Locale.getDefault()).format(this)
