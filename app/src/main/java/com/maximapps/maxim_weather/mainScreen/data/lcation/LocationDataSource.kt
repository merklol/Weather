package com.maximapps.maxim_weather.mainScreen.data.lcation

import android.annotation.SuppressLint
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.tasks.CancellationTokenSource
import com.maximapps.maxim_weather.common.utils.asSingle
import com.maximapps.maxim_weather.common.utils.millisecondsToHours
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@SuppressLint("MissingPermission")
class LocationDataSource @Inject constructor(private val client: FusedLocationProviderClient) {
    private val token = CancellationTokenSource().token

    fun getLocation(): Single<Coordinates> = with(client) {
        lastLocation.asSingle(CancellationTokenSource()).filter {
            (System.currentTimeMillis() - it.time).millisecondsToHours() <= 1
        }.switchIfEmpty(
            getCurrentLocation(PRIORITY_HIGH_ACCURACY, token).asSingle(
                CancellationTokenSource()
            )
        ).map { Coordinates(it.latitude, it.longitude) }
    }
}

