package com.maximapps.maxim_weather.common.utils

import java.util.concurrent.TimeUnit

/**
 * Returns the converted duration as Hours.
 */
fun Long.millisecondsToHours() = TimeUnit.MILLISECONDS.toHours(this)