package com.maximapps.maxim_weather.mainScreen.data.location

import androidx.annotation.RequiresPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.tasks.CancellationTokenSource
import com.maximapps.maxim_weather.common.utils.asSingle
import com.maximapps.maxim_weather.common.utils.millisecondsToHours
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LocationDataSource @Inject constructor(private val client: FusedLocationProviderClient) {
    private val cancellationTokenSource = CancellationTokenSource()

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    fun getLocation(): Single<Location> = client.lastLocation
        .asSingle(cancellationTokenSource)
        .filter { (System.currentTimeMillis() - it.time).millisecondsToHours() <= 1 }
        .switchIfEmpty(getCurrentLocation())
        .map { Location(it.latitude, it.longitude) }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    private fun getCurrentLocation() = client
        .getCurrentLocation(PRIORITY_HIGH_ACCURACY, cancellationTokenSource.token)
        .asSingle(cancellationTokenSource)
}
