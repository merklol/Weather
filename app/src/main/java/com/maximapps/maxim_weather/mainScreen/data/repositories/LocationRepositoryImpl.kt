package com.maximapps.maxim_weather.mainScreen.data.repositories

import androidx.annotation.RequiresPermission
import com.maximapps.maxim_weather.mainScreen.data.location.LocationDataSource
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates.Coordinates
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates.LocationRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Default implementation of the [LocationRepository] interface.
 */
class LocationRepositoryImpl @Inject constructor(
    private val dataSource: LocationDataSource
) : LocationRepository {

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    override fun getLocation(): Single<Coordinates> = dataSource
        .getLocation()
        .map { Coordinates(it.latitude, it.longitude) }
        .subscribeOn(Schedulers.io())
}
