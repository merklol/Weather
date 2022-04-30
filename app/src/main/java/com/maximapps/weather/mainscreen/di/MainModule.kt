package com.maximapps.weather.mainscreen.di

import com.maximapps.weather.mainscreen.repositories.location.LocationRepositoryImpl
import com.maximapps.weather.mainscreen.repositories.weather.WeatherRepository
import com.maximapps.weather.mainscreen.usecases.fetchforecastbycoordinates.FETCH_FORECAST_BY_COORDINATES
import com.maximapps.weather.mainscreen.usecases.fetchforecastbycoordinates.FetchForecastByCoordinates
import com.maximapps.weather.mainscreen.usecases.fetchforecastbycoordinates.FetchForecastByCoordinatesImpl
import com.maximapps.weather.mainscreen.usecases.fetchforecastbycoordinates.LocationRepository
import com.maximapps.weather.mainscreen.usecases.fetchforecastbycoordinates.LocationWeatherRepository
import com.maximapps.weather.mainscreen.usecases.fetchforecastbyname.CityWeatherRepository
import com.maximapps.weather.mainscreen.usecases.fetchforecastbyname.FETCH_FORECAST_BY_NAME
import com.maximapps.weather.mainscreen.usecases.fetchforecastbyname.FetchForecastByName
import com.maximapps.weather.mainscreen.usecases.fetchforecastbyname.FetchForecastByNameImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@Suppress("unused")
interface MainModule {

    @Binds
    @Singleton
    fun bindsLocationWeatherRepository(repository: WeatherRepository): LocationWeatherRepository

    @Binds
    @Singleton
    fun bindsCityWeatherRepository(repository: WeatherRepository): CityWeatherRepository

    @Binds
    fun bindsLocationRepository(repository: LocationRepositoryImpl): LocationRepository

    @Binds
    @Named(FETCH_FORECAST_BY_NAME)
    fun bindsFetchForecastByNameUseCase(useCase: FetchForecastByNameImpl): FetchForecastByName

    @Binds
    @Named(FETCH_FORECAST_BY_COORDINATES)
    fun bindsFetchForecastByCoordinatesUseCase(useCase: FetchForecastByCoordinatesImpl): FetchForecastByCoordinates
}
