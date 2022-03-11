package com.maximapps.maxim_weather.mainScreen.data.lcation

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.tasks.CancellationTokenSource
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@SuppressLint("MissingPermission")
class LocationDataSource @Inject constructor(private val client: FusedLocationProviderClient) {
    private val token = CancellationTokenSource().token

    fun getLocation(): Single<Location> = Single.create { emitter ->
        client.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                if (TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis() - it.time) > 1) {
                    client.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, token)
                        .addOnSuccessListener { data -> emitter.onSuccess(data) }
                        .addOnFailureListener { exception -> emitter.onError(exception) }
                } else {
                    emitter.onSuccess(it)
                }
            } ?: client.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, token)
                .addOnSuccessListener { data -> emitter.onSuccess(data) }
                .addOnFailureListener { exception -> emitter.onError(exception) }
        }
    }
}
