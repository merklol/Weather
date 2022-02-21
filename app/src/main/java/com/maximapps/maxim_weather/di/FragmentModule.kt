package com.maximapps.maxim_weather.di

import com.maximapps.maxim_weather.ui.main.MainDialogFragment
import com.maximapps.maxim_weather.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("unused")
interface FragmentModule {
    @ContributesAndroidInjector
    fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    fun contributeMainDialog(): MainDialogFragment
}
