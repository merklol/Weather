package com.maximapps.maxim_weather.network.models

import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("3h")
    val threeHour: Double
)