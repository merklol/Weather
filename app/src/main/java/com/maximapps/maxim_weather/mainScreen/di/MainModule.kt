package com.maximapps.maxim_weather.mainScreen.di

import androidx.lifecycle.ViewModel
import com.maximapps.maxim_weather.common.di.factory.ViewModelKey
import com.maximapps.maxim_weather.mainScreen.data.location.FusedLocationDataSource
import com.maximapps.maxim_weather.mainScreen.data.location.LocationDataSource
import com.maximapps.maxim_weather.mainScreen.data.repositories.DefaultLocationRepository
import com.maximapps.maxim_weather.mainScreen.data.repositories.DefaultWeatherRepository
import com.maximapps.maxim_weather.mainScreen.domain.repositories.LocationRepository
import com.maximapps.maxim_weather.mainScreen.domain.repositories.WeatherRepository
import com.maximapps.maxim_weather.mainScreen.domain.usecases.FetchForecastByCoordinatesImpl
import com.maximapps.maxim_weather.mainScreen.domain.usecases.FetchForecastByCoordinates
import com.maximapps.maxim_weather.mainScreen.domain.usecases.FetchForecastByNameImpl
import com.maximapps.maxim_weather.mainScreen.domain.usecases.FetchForecastByName
import com.maximapps.maxim_weather.mainScreen.ui.MainViewModel
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
    fun bindsWeatherRepository(repository: DefaultWeatherRepository): WeatherRepository

    @Binds
    fun bindsLocationRepository(repository: DefaultLocationRepository): LocationRepository

    @Binds
    @Named("FetchForecastByName")
    fun bindsFetchForecastByNameUseCase(useCase: FetchForecastByNameImpl): FetchForecastByName

    @Binds
    @Named("FetchForecastByCoordinates")
    fun bindsFetchForecastByCoordinatesUseCase(useCase: FetchForecastByCoordinatesImpl): FetchForecastByCoordinates
}
