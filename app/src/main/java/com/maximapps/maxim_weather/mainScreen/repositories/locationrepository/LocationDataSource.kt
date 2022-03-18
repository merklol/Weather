package com.maximapps.maxim_weather.mainScreen.repositories.locationrepository

import io.reactivex.rxjava3.core.Single

/**
 * Provides the most recent location available.
 */
interface LocationDataSource {
    fun getLocation(): Single<Location>
}
