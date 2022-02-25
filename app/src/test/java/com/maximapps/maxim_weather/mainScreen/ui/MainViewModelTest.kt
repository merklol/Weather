package com.maximapps.maxim_weather.mainScreen.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.mainScreen.domain.WeatherRepository
import com.maximapps.maxim_weather.mainScreen.domain.models.WeatherData
import com.maximapps.maxim_weather.utils.RxImmediateSchedulerRule
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.reactivex.rxjava3.core.Single
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers

@RunWith(JUnit4::class)
class MainViewModelTest {
    @get:Rule
    var testScheduler = RxImmediateSchedulerRule()

    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var liveDataObserver: Observer<MainState>

    @MockK
    lateinit var repository: WeatherRepository

    private lateinit var viewModel: MainViewModel
    private val slots = arrayListOf<MainState>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(repository)
        val slot = slot<MainState>()
        every { liveDataObserver.onChanged(capture(slot)) } answers { slots.add(slot.captured) }

    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `when getForecast return good response the state switch to Loading and then to Success`() {
        val data = WeatherData("", listOf())
        every { repository.getForecast(ArgumentMatchers.anyString()) } returns Single.just(data)

        viewModel.liveData.observeForever(liveDataObserver)
        viewModel.getForecast(ArgumentMatchers.anyString())

        MatcherAssert.assertThat(slots[0], CoreMatchers.`is`(MainState.Loading))
        MatcherAssert.assertThat(
            slots[1],
            CoreMatchers.`is`(MainState.Loaded(data.cityName, data.detailedForecast))
        )
    }

    @Test
    fun `when getForecast return bad response the state switch to Loading and then to Fail`() {
        every { repository.getForecast(ArgumentMatchers.anyString()) } returns Single.error(
            Exception()
        )

        viewModel.liveData.observeForever(liveDataObserver)
        viewModel.getForecast(ArgumentMatchers.anyString())

        MatcherAssert.assertThat(slots[0], CoreMatchers.`is`(MainState.Loading))
        MatcherAssert.assertThat(
            slots[1],
            CoreMatchers.`is`(MainState.Error(R.string.error_message))
        )
    }
}
