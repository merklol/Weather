package com.maximapps.maxim_weather.di

import android.app.Application
import com.maximapps.maxim_weather.App
import com.maximapps.maxim_weather.di.viewmodels.ViewModelFactoryModule
import com.maximapps.maxim_weather.di.viewmodels.ViewModelsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainModule::class,
        FragmentModule::class,
        ViewModelFactoryModule::class,
        ViewModelsModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
}
