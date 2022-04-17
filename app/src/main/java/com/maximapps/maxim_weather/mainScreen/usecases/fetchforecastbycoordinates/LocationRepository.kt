package com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates

import io.reactivex.rxjava3.core.Single

/**
 * Interface defining methods for getting device location.
 */
interface LocationRepository {
    /**
     * Provides device geographic coordinates.
     */
    fun getLocation(): Single<Coordinates>
}
