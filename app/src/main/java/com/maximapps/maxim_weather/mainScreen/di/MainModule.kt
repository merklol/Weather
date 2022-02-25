package com.maximapps.maxim_weather.mainScreen.di

import com.maximapps.maxim_weather.mainScreen.data.DefaultWeatherRepository
import com.maximapps.maxim_weather.mainScreen.data.network.WeatherService
import com.maximapps.maxim_weather.mainScreen.domain.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@Suppress("unused")
interface MainModule {
    @Binds
    @Singleton
    fun bindsWeatherRepository(repository: DefaultWeatherRepository): WeatherRepository

    companion object {
        @Provides
        @Singleton
        fun providesOpenWeatherService(retrofit: Retrofit): WeatherService =
            retrofit.create(WeatherService::class.java)
    }
}
