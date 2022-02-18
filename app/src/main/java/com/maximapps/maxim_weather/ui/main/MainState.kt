package com.maximapps.maxim_weather.ui.main

import com.maximapps.maxim_weather.domain.models.CityWeather

sealed class MainState {
    object Loading : MainState()
    data class Success(val response: CityWeather) : MainState()
    data class Fail(val errorMessage: String) : MainState()
}