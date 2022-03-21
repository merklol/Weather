package com.maximapps.maxim_weather

import com.maximapps.maxim_weather.mainScreen.repositories.WeatherRepositoryTest
import com.maximapps.maxim_weather.mainScreen.ui.MainViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    WeatherRepositoryTest::class,
    MainViewModelTest::class
)
class AllTestsSuite
