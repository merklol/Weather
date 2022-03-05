package com.maximapps.maxim_weather.mainScreen.ui

import app.cash.turbine.test
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.mainScreen.domain.WeatherRepository
import com.maximapps.maxim_weather.mainScreen.domain.models.WeatherData
import com.maximapps.maxim_weather.utils.RxImmediateSchedulerRule
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.rxjava3.core.Single
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {
    @get:Rule
    var testScheduler = RxImmediateSchedulerRule()

    @MockK
    lateinit var repository: WeatherRepository
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = MainViewModel(repository)
    }

    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

    @Test
    fun `when getNewForecast return a good response then should show a weather list`() = runTest {
        every { repository.fetchForecast(anyString()) } returns Single.just(WeatherData())
        viewModel.fetchNewForecast(anyString())
        assertThat(viewModel.weatherData.value, `is`(emptyList()))
    }

    @Test
    fun `when getNewForecast return a bad response then should show an error message`() = runTest {
        every { repository.fetchForecast(anyString()) } returns Single.error(Exception())
        launch {
            viewModel.errorMessage.test {
                viewModel.fetchNewForecast(anyString())
                assertEquals(R.string.error_message, awaitItem())
            }
        }
    }
}
