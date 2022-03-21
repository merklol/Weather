package com.maximapps.maxim_weather.mainScreen.repositories.location

import android.content.Context
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.maximapps.maxim_weather.common.utils.asSingle
import com.maximapps.maxim_weather.common.utils.millisecondsToHours
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates.Coordinates
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates.LocationRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Default implementation of the [LocationRepository] interface.
 */
class LocationRepositoryImpl @Inject constructor(context: Context) : LocationRepository {
    private val client = LocationServices.getFusedLocationProviderClient(context)
    private val cancellationTokenSource = CancellationTokenSource()

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    override fun getLocation(): Single<Coordinates> = client.lastLocation
        .asSingle(cancellationTokenSource)
        .filter { (System.currentTimeMillis() - it.time).millisecondsToHours() <= 1 }
        .switchIfEmpty(getCurrentLocation())
        .map { Coordinates(it.latitude, it.longitude) }
        .subscribeOn(Schedulers.io())

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    private fun getCurrentLocation() = client
        .getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, cancellationTokenSource.token)
        .asSingle(cancellationTokenSource)
}
