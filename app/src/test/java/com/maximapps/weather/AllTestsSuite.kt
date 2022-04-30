package com.maximapps.weather

import com.maximapps.weather.mainscreen.repositories.WeatherRepositoryTest
import com.maximapps.weather.mainscreen.ui.MainViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    WeatherRepositoryTest::class,
    MainViewModelTest::class
)
class AllTestsSuite
