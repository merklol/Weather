package com.maximapps.maxim_weather.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Forecast(
    val date: Date,
    val temperature: Int,
    val temperatureMin: Int,
    val temperatureMax: Int,
    val wind: Int,
    val feelsLike: Int,
    val weather: String,
    val iconResId: Int,
    val details: List<DetailedForecast>
) : Parcelable
