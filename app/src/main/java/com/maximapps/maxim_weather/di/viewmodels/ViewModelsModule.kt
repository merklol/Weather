package com.maximapps.maxim_weather.di.viewmodels

import androidx.lifecycle.ViewModel
import com.maximapps.maxim_weather.mainScreen.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
@Suppress("unused")
interface ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}
