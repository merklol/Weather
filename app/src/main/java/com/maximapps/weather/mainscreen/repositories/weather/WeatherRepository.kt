package com.maximapps.weather.mainscreen.repositories.weather

import com.maximapps.weather.mainscreen.repositories.weather.network.WeatherService
import com.maximapps.weather.mainscreen.usecases.common.WeatherData
import com.maximapps.weather.mainscreen.usecases.fetchforecastbycoordinates.Coordinates
import com.maximapps.weather.mainscreen.usecases.fetchforecastbycoordinates.LocationWeatherRepository
import com.maximapps.weather.mainscreen.usecases.fetchforecastbyname.CityWeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Default implementation of the [LocationWeatherRepository], [CityWeatherRepository] interfaces.
 */
class WeatherRepository @Inject constructor(
    private val service: WeatherService,
    private val mapper: ResponseMapper
) : LocationWeatherRepository, CityWeatherRepository {

    override fun fetchWeatherForecast(coordinates: Coordinates): Flow<WeatherData> = flow {
        val res = service.fetchWeatherForecast(coordinates.latitude, coordinates.longitude)
        emit(mapper.map(res))
    }.flowOn(Dispatchers.IO)

    override fun fetchWeatherForecast(cityName: String): Flow<WeatherData> = flow {
        val res = mapper.map(service.fetchWeatherForecast(cityName))
        emit(res)
    }.flowOn(Dispatchers.IO)
}
