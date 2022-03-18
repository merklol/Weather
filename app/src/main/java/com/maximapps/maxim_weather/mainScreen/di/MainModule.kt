package com.maximapps.maxim_weather.mainScreen.di

import androidx.lifecycle.ViewModel
import com.maximapps.maxim_weather.common.di.factory.ViewModelKey
import com.maximapps.maxim_weather.mainScreen.data.location.FusedLocationDataSource
import com.maximapps.maxim_weather.mainScreen.data.location.LocationDataSource
import com.maximapps.maxim_weather.mainScreen.data.repositories.CityWeatherRepositoryImpl
import com.maximapps.maxim_weather.mainScreen.data.repositories.LocationRepositoryImpl
import com.maximapps.maxim_weather.mainScreen.data.repositories.LocationWeatherRepositoryImpl
import com.maximapps.maxim_weather.mainScreen.ui.MainViewModel
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates.FetchForecastByCoordinates
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates.FetchForecastByCoordinatesImpl
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates.LocationRepository
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates.LocationWeatherRepository
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbyname.CityWeatherRepository
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbyname.FetchForecastByName
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbyname.FetchForecastByNameImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, FragmentModule::class])
@Suppress("unused")
interface MainModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindsMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @Singleton
    fun bindsLocationDataSource(repository: FusedLocationDataSource): LocationDataSource

    @Binds
    @Singleton
    fun bindsLocationWeatherRepository(repository: LocationWeatherRepositoryImpl): LocationWeatherRepository

    @Binds
    @Singleton
    fun bindsCityWeatherRepository(repository: CityWeatherRepositoryImpl): CityWeatherRepository

    @Binds
    fun bindsLocationRepository(repository: LocationRepositoryImpl): LocationRepository

    @Binds
    @Named("FetchForecastByName")
    fun bindsFetchForecastByNameUseCase(useCase: FetchForecastByNameImpl): FetchForecastByName

    @Binds
    @Named("FetchForecastByCoordinates")
    fun bindsFetchForecastByCoordinatesUseCase(useCase: FetchForecastByCoordinatesImpl): FetchForecastByCoordinates
}
