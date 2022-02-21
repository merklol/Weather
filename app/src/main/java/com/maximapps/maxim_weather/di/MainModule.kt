package com.maximapps.maxim_weather.di

import com.maximapps.maxim_weather.data.DefaultWeatherRepository
import com.maximapps.maxim_weather.domain.WeatherRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
@Suppress("unused")
interface MainModule {

    @Binds
    @Singleton
    fun bindsWeatherRepository(repository: DefaultWeatherRepository): WeatherRepository
}
