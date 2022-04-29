package com.maximapps.maxim_weather.mainScreen.repositories.location

import android.content.Context
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.maximapps.maxim_weather.common.utils.asFlow
import com.maximapps.maxim_weather.common.utils.millisecondsToHours
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates.Coordinates
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.takeWhile
import javax.inject.Inject

/**
 * Default implementation of the [LocationRepository] interface.
 */
class LocationRepositoryImpl @Inject constructor(context: Context) : LocationRepository {
    private val client = LocationServices.getFusedLocationProviderClient(context)
    private val cancellationTokenSource = CancellationTokenSource()

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    override fun getLocation(): Flow<Coordinates> = client.lastLocation
        .asFlow(cancellationTokenSource)
        .takeWhile { it != null && timeDifference(System.currentTimeMillis(), it.time) <= 1 }
        .onEmpty { emit(getCurrentLocation()) }
        .map { Coordinates(it.latitude, it.longitude) }
        .flowOn(Dispatchers.IO)

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    private suspend fun getCurrentLocation() = client
        .getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, cancellationTokenSource.token)
        .asFlow(cancellationTokenSource)
        .first()

    private fun timeDifference(currentTimeInMillis: Long, previousTimeInMillis: Long) =
        (currentTimeInMillis - previousTimeInMillis).millisecondsToHours()
}
