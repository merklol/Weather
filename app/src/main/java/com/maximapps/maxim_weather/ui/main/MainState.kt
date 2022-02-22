package com.maximapps.maxim_weather.ui.main

import androidx.annotation.StringRes
import com.maximapps.maxim_weather.domain.models.DetailedForecast

sealed class MainState {
    object Loading : MainState()
    data class Loaded(
        val cityName: String,
        val detailedForecast: List<DetailedForecast>
    ) : MainState()

    data class Error(@StringRes val resId: Int) : MainState()
}
