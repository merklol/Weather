package com.maximapps.maxim_weather.ui.main

import androidx.annotation.StringRes
import com.maximapps.maxim_weather.domain.models.WeatherData

sealed class MainState {
    object Loading : MainState()
    data class Success(val data: WeatherData) : MainState()
    data class Fail(@StringRes val resId: Int) : MainState()
}
