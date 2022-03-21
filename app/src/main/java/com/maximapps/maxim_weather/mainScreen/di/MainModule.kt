package com.maximapps.maxim_weather.mainScreen.di

import androidx.lifecycle.ViewModel
import com.maximapps.maxim_weather.common.di.factory.ViewModelKey
import com.maximapps.maxim_weather.mainScreen.repositories.location.LocationRepositoryImpl
import com.maximapps.maxim_weather.mainScreen.repositories.weather.WeatherRepository
import com.maximapps.maxim_weather.mainScreen.ui.MainViewModel
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates.FETCH_FORECAST_BY_COORDINATES
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates.FetchForecastByCoordinates
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates.FetchForecastByCoordinatesImpl
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates.LocationRepository
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates.LocationWeatherRepository
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbyname.CityWeatherRepository
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbyname.FETCH_FORECAST_BY_NAME
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
