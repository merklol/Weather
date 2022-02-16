package com.maximapps.maxim_weather.ui

import com.maximapps.maxim_weather.network.models.Response


sealed class MainState {
    object Loading: MainState()
    data class Success(val response: Response): MainState()
    data class Fail(val errorMessage: String): MainState()
}