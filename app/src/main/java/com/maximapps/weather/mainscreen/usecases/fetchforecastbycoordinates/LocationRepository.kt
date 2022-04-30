package com.maximapps.weather.mainscreen.usecases.fetchforecastbycoordinates

import kotlinx.coroutines.flow.Flow

/**
 * Interface defining methods for getting device location.
 */
interface LocationRepository {
    /**
     * Provides device geographic coordinates.
     */
    fun getLocation(): Flow<Coordinates>
}
