package com.maximapps.maxim_weather.mainScreen.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.mainScreen.domain.WeatherRepository
import com.maximapps.maxim_weather.mainScreen.domain.models.DetailedForecast
import com.maximapps.maxim_weather.mainScreen.domain.models.WeatherData
import com.maximapps.maxim_weather.utils.RxImmediateSchedulerRule
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString

@RunWith(JUnit4::class)
class MainViewModelTest {
    @get:Rule
    var testScheduler = RxImmediateSchedulerRule()

    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @MockK
    lateinit var isLoadingObserver: Observer<Boolean>

    @MockK
    lateinit var dataObserver: Observer<List<DetailedForecast>>

    @MockK
    lateinit var errorObserver: Observer<Int>

    @MockK
    lateinit var repository: WeatherRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(repository)
        every { isLoadingObserver.onChanged(any()) } answers { }
        every { dataObserver.onChanged(any()) } answers { }
        every { errorObserver.onChanged(any()) } answers { }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `when getForecast return good response the state switch to Loading and then to Success`() {
        every { repository.fetchForecast(anyString()) } returns Single.just(WeatherData())
        viewModel.loaderVisibility.observeForever(isLoadingObserver)
        viewModel.weatherData.observeForever(dataObserver)

        viewModel.fetchForecast(anyString())

        verify { isLoadingObserver.onChanged(true) }
        verify { dataObserver.onChanged(emptyList()) }
    }

    @Test
    fun `when getForecast return bad response the state switch to Loading and then to Fail`() {
        every { repository.fetchForecast(anyString()) } returns Single.error(Exception())
        viewModel.loaderVisibility.observeForever(isLoadingObserver)
        viewModel.errorMessage.observeForever(errorObserver)

        viewModel.fetchForecast(anyString())

        verify { isLoadingObserver.onChanged(true) }
        verify { errorObserver.onChanged(R.string.error_message) }
    }
}
