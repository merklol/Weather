package com.maximapps.maxim_weather.mainScreen.data.network.models

import com.google.gson.annotations.SerializedName
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit

data class Response(
    val cod: String,
    val message: String,
    val city: CityEntity,
    @SerializedName("list")
    val forecastList: List<ForecastEntity>
) {
    fun groupByDate() = forecastList.groupBy {
        with(Calendar.getInstance()) {
            time = Date(TimeUnit.SECONDS.toMillis(it.dt))
            get(Calendar.DATE)
        }
    }
}
