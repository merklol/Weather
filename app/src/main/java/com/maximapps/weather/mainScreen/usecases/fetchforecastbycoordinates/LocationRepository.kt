package com.maximapps.weather.mainScreen.usecases.fetchforecastbycoordinates

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
