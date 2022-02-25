package com.maximapps.maxim_weather.mainScreen.data.network.models

import com.google.gson.annotations.SerializedName

data class RainEntity(
    @SerializedName("3h")
    val threeHour: Double
)