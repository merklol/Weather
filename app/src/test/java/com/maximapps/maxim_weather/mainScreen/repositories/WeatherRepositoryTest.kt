package com.maximapps.maxim_weather.mainScreen.repositories

import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.mainScreen.repositories.weather.ForecastMapper
import com.maximapps.maxim_weather.mainScreen.repositories.weather.IconMapper
import com.maximapps.maxim_weather.mainScreen.repositories.weather.ResponseMapper
import com.maximapps.maxim_weather.mainScreen.repositories.weather.WeatherRepository
import com.maximapps.maxim_weather.mainScreen.repositories.weather.network.Response
import com.maximapps.maxim_weather.mainScreen.repositories.weather.network.WeatherService
import com.maximapps.maxim_weather.mainScreen.usecases.common.DetailedForecast
import com.maximapps.maxim_weather.mainScreen.usecases.common.UNDEFINED
import com.maximapps.maxim_weather.mainScreen.usecases.common.WeatherData
import com.maximapps.maxim_weather.mainScreen.usecases.common.WeatherForecast
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbyname.CityWeatherRepository
import com.maximapps.maxim_weather.utils.readJsonFileToObject
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.Date
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class WeatherRepositoryTest {
    @MockK
    lateinit var weatherService: WeatherService
    private lateinit var repository: CityWeatherRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        val iconMapper = IconMapper()
        val responseMapper = ResponseMapper(iconMapper, ForecastMapper(iconMapper))
        repository = WeatherRepository(weatherService, responseMapper)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `when response is correct should map it to domain models with correctly formatted values`() =
        runTest {
            val json = "api-response/forecast_response.json"
            val response = javaClass.readJsonFileToObject(json, Response::class.java)
            coEvery { weatherService.fetchWeatherForecast(any()) } returns response

            val expected = WeatherData(
                cityName = "Moscow", detailedForecast = listOf(
                    DetailedForecast(
                        date = Date(TimeUnit.SECONDS.toMillis(1645920000)),
                        temperature = -1,
                        temperatureMin = -1,
                        temperatureMax = -1,
                        wind = 2,
                        weatherCondition = "Overcast clouds",
                        feelsLike = -3,
                        weatherIcon = R.mipmap.few_clouds,
                        forecastList = listOf(
                            WeatherForecast(
                                date = Date(TimeUnit.SECONDS.toMillis(1645920000)),
                                temperature = -1,
                                minTemperature = -1,
                                maxTemperature = -1,
                                weatherIcon = R.mipmap.few_clouds
                            )
                        )
                    )
                )
            )
            assertThat(repository.fetchWeatherForecast("Moscow").first(), `is`(expected))
        }

    @Test
    fun `when response does not contain weather data return weather condition as undefined`() =
        runTest {
            val json = "api-response/forecast_response_without_weather_data.json"
            val response = javaClass.readJsonFileToObject(json, Response::class.java)
            coEvery { weatherService.fetchWeatherForecast(any()) } returns response

            val result = repository.fetchWeatherForecast("Shanghai").first()
            assertThat(result.detailedForecast.first().weatherCondition, `is`(UNDEFINED))
        }

    @Test
    fun `when response does not contain weather data return default icon type`() = runTest {
        val json = "api-response/forecast_response_without_weather_data.json"
        val response = javaClass.readJsonFileToObject(json, Response::class.java)
        coEvery { weatherService.fetchWeatherForecast(any()) } returns response

        val result = repository.fetchWeatherForecast("Shanghai").first()
        assertThat(result.detailedForecast.first().weatherIcon, `is`(R.mipmap.few_clouds))
    }
}
