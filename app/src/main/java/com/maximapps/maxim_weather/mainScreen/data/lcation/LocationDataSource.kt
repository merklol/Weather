package com.maximapps.maxim_weather.mainScreen.data.lcation

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.tasks.CancellationTokenSource
import com.maximapps.maxim_weather.common.utils.asSingle
import com.maximapps.maxim_weather.common.utils.toHours
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@SuppressLint("MissingPermission")
class LocationDataSource @Inject constructor(private val client: FusedLocationProviderClient) {
    private val token = CancellationTokenSource().token

    fun getLocation(): Single<Location> =
        client.lastLocation.asSingle().flatMap { location: Location? ->
            location?.let {
                if ((System.currentTimeMillis() - it.time).toHours() > 1) {
                    client.getCurrentLocation(PRIORITY_HIGH_ACCURACY, token).asSingle()
                } else {
                    Single.just(it)
                }
            } ?: client.getCurrentLocation(PRIORITY_HIGH_ACCURACY, token).asSingle()
        }
}
