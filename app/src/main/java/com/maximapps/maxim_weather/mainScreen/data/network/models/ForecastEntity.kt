package com.maximapps.maxim_weather.mainScreen.data.network.models

import com.google.gson.annotations.SerializedName

data class ForecastEntity(
    val dt: Long,
    val main: MainEntity,
    val weather: List<WeatherEntity>,
    val clouds: CloudsEntity,
    val wind: WindEntity,
    val visibility: Int,
    val pop: Double,
    val rain: RainEntity?,
    val sys: SysEntity,
    @SerializedName("dt_txt")
    val dtTxt: String
)
