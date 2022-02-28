package com.maximapps.maxim_weather.common.di

import android.app.Application
import com.maximapps.maxim_weather.common.App
import com.maximapps.maxim_weather.common.di.factory.ViewModelFactoryModule
import com.maximapps.maxim_weather.mainScreen.di.MainModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        MainModule::class,
        ViewModelFactoryModule::class,
        AndroidInjectionModule::class,
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
