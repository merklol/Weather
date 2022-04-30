package com.maximapps.weather.mainScreen.ui

import com.maximapps.weather.R
import com.maximapps.weather.mainScreen.usecases.common.WeatherData
import com.maximapps.weather.mainScreen.usecases.fetchforecastbycoordinates.FetchForecastByCoordinates
import com.maximapps.weather.mainScreen.usecases.fetchforecastbyname.FetchForecastByName
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {
    @MockK
    lateinit var fetchForecastByName: FetchForecastByName
    @MockK
    lateinit var fetchForecastByCoordinates: FetchForecastByCoordinates
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = MainViewModel(fetchForecastByName, fetchForecastByCoordinates)
    }

    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

    @Test
    fun `when fetchNewForecast return data then should show a weather list`() = runTest {
        every { fetchForecastByName(any()) } returns flowOf(WeatherData())
        viewModel.fetchNewForecast("Shanghai")
        assertThat(viewModel.weatherData.value, `is`(emptyList()))
    }

    @Test
    fun `when fetchNewForecast return error then should show an error message`() =
        runTest(UnconfinedTestDispatcher()) {
            every { fetchForecastByName(any()) } returns flow { throw Exception() }
            val result = async { viewModel.errorMessage.first() }
            viewModel.fetchNewForecast("Shanghai")
            assertEquals(R.string.error_message, result.await())
        }

    @Test
    fun `when fetchNewForecastByLocation return data and permission granted then should show weather`() =
        runTest {
            every { fetchForecastByCoordinates() } returns flowOf(WeatherData())
            viewModel.fetchNewForecastByLocation(true)
            assertThat(viewModel.weatherData.value, `is`(emptyList()))
        }

    @Test
    fun `when fetchNewForecastByLocation return error & permission is granted then should show an error message`() =
        runTest(UnconfinedTestDispatcher()) {
            every { fetchForecastByCoordinates() } returns flow { throw  Exception() }
            val result = async { viewModel.errorMessage.first() }
            viewModel.fetchNewForecastByLocation(true)
            assertEquals(R.string.error_message, result.await())
        }

    @Test
    fun `when permission is not granted then should show a rationale dialog`() =
        runTest(UnconfinedTestDispatcher()) {
            every { fetchForecastByCoordinates() } returns flowOf(WeatherData())
            val result = async { viewModel.rationaleDialogVisibility.first() }
            viewModel.fetchNewForecastByLocation(false)
            assertEquals(Unit, result.await())
        }
}
