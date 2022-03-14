package com.maximapps.maxim_weather.mainScreen.data.lcation

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.tasks.CancellationTokenSource
import com.maximapps.maxim_weather.common.utils.asSingle
import com.maximapps.maxim_weather.common.utils.millisecondsToHours
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@SuppressLint("MissingPermission")
class LocationDataSource @Inject constructor(private val client: FusedLocationProviderClient) {
    private val cancellationTokenSource = CancellationTokenSource()

    fun getLocation(): Single<Coordinates> = client.lastLocation
        .asSingle(cancellationTokenSource)
        .filter { (System.currentTimeMillis() - it.time).millisecondsToHours() <= 1 }
        .switchIfEmpty(getCurrentLocation())
        .map { Coordinates(it.latitude, it.longitude) }

    private fun getCurrentLocation() = client
        .getCurrentLocation(PRIORITY_HIGH_ACCURACY, cancellationTokenSource.token)
        .asSingle(cancellationTokenSource)
}
