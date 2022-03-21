package com.maximapps.maxim_weather.mainScreen.repositories.locationrepository.location

import android.content.Context
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.maximapps.maxim_weather.common.utils.asSingle
import com.maximapps.maxim_weather.common.utils.millisecondsToHours
import com.maximapps.maxim_weather.mainScreen.repositories.locationrepository.Location
import com.maximapps.maxim_weather.mainScreen.repositories.locationrepository.LocationDataSource
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Implementations of [LocationDataSource] interface using FusedLocationProviderClient.
 */
class FusedLocationDataSource @Inject constructor(context: Context) : LocationDataSource {
    private val client = LocationServices.getFusedLocationProviderClient(context)
    private val cancellationTokenSource = CancellationTokenSource()

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    override fun getLocation(): Single<Location> = client.lastLocation
        .asSingle(cancellationTokenSource)
        .filter { (System.currentTimeMillis() - it.time).millisecondsToHours() <= 1 }
        .switchIfEmpty(getCurrentLocation())
        .map { Location(it.latitude, it.longitude) }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    private fun getCurrentLocation() = client
        .getCurrentLocation(PRIORITY_HIGH_ACCURACY, cancellationTokenSource.token)
        .asSingle(cancellationTokenSource)
}
