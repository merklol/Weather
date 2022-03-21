package com.maximapps.maxim_weather.mainScreen.di

import com.maximapps.maxim_weather.mainScreen.repositories.weather.network.WeatherService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun providesOpenWeatherService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)
}
