package com.maximapps.maxim_weather.mainScreen.domain.repositories

import com.maximapps.maxim_weather.mainScreen.domain.models.Coordinates
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
