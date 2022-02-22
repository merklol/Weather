package com.maximapps.maxim_weather.domain.models

sealed class ResponseResult {
    data class Success(
        val cityName: String,
        val detailedForecast: List<DetailedForecast>
    ) : ResponseResult()

    object Error : ResponseResult()
}
