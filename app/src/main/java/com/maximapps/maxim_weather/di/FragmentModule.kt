package com.maximapps.maxim_weather.di

import com.maximapps.maxim_weather.ui.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
interface FragmentModule {
    @ContributesAndroidInjector
    fun contributeMainFragment(): MainFragment
}