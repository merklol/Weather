package com.maximapps.maxim_weather.mainScreen.di

import androidx.lifecycle.ViewModel
import com.maximapps.maxim_weather.common.di.factory.ViewModelKey
import com.maximapps.maxim_weather.mainScreen.data.DefaultWeatherRepository
import com.maximapps.maxim_weather.mainScreen.domain.WeatherRepository
import com.maximapps.maxim_weather.mainScreen.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
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
    fun bindsWeatherRepository(repository: DefaultWeatherRepository): WeatherRepository
}
