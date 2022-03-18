package com.maximapps.maxim_weather

import com.maximapps.maxim_weather.mainScreen.repositories.CityWeatherRepositoryImplTest
import com.maximapps.maxim_weather.mainScreen.repositories.LocationWeatherRepositoryImplTest
import com.maximapps.maxim_weather.mainScreen.ui.MainViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    CityWeatherRepositoryImplTest::class,
    LocationWeatherRepositoryImplTest::class,
    MainViewModelTest::class
)
class AllTestsSuite
