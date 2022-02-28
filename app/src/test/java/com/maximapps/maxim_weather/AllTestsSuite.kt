package com.maximapps.maxim_weather

import com.maximapps.maxim_weather.mainScreen.data.DefaultWeatherRepositoryTest
import com.maximapps.maxim_weather.mainScreen.ui.MainViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(DefaultWeatherRepositoryTest::class, MainViewModelTest::class)
class AllTestsSuite