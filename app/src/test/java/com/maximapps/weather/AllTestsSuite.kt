package com.maximapps.weather

import com.maximapps.weather.mainScreen.repositories.WeatherRepositoryTest
import com.maximapps.weather.mainScreen.ui.MainViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    WeatherRepositoryTest::class,
    MainViewModelTest::class
)
class AllTestsSuite
