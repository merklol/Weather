package com.maximapps.maxim_weather.mainScreen.data

import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.mainScreen.data.network.WeatherService
import com.maximapps.maxim_weather.mainScreen.data.repositories.DefaultWeatherRepository
import com.maximapps.maxim_weather.mainScreen.domain.models.DetailedForecast
import com.maximapps.maxim_weather.mainScreen.domain.models.Undefined
import com.maximapps.maxim_weather.mainScreen.domain.models.WeatherData
import com.maximapps.maxim_weather.mainScreen.domain.models.WeatherForecast
import com.maximapps.maxim_weather.mainScreen.domain.repositories.WeatherRepository
import com.maximapps.maxim_weather.utils.RxImmediateSchedulerRule
import com.maximapps.maxim_weather.utils.readFileFromResources
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.observers.TestObserver
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class DefaultWeatherRepositoryTest {
    @get:Rule
    var testScheduler = RxImmediateSchedulerRule()
    private val webServer = MockWebServer()
    private val api = Retrofit.Builder()
        .baseUrl(webServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherService::class.java)

    private lateinit var repository: WeatherRepository

    @Before
    fun setup() {
        val iconMapper = IconMapper()
        repository =
            DefaultWeatherRepository(
                api,
                ResponseMapper(iconMapper, ForecastMapper(iconMapper)),
            )
    }

    @After
    fun tearDown() {
        webServer.shutdown()
    }

    @Test
    fun `when response is correct should map it to domain models with correctly formatted values`() {
        val json = "api-response/forecast_response.json"
        val testObserver = TestObserver<WeatherData>()
        webServer.enqueue(
            MockResponse().setBody(javaClass.classLoader.readFileFromResources(json))
        )
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
        repository.fetchForecastByName(ArgumentMatchers.anyString()).subscribe(testObserver)
        testObserver.assertValue(expected)

    }

    @Test
    fun `when response does not contain weather data return weather condition as undefined`() {
        val json = "api-response/forecast_response_without_weather_data.json"
        val testObserver = TestObserver<WeatherData>()
        webServer.enqueue(
            MockResponse().setBody(javaClass.classLoader.readFileFromResources(json))
        )
        repository.fetchForecastByName(ArgumentMatchers.anyString()).subscribe(testObserver)
        testObserver.assertValue {
            it.detailedForecast[0].weatherCondition == Undefined
        }
    }

    @Test
    fun `when response does not contain weather data return default icon type`() {
        val json = "api-response/forecast_response_without_weather_data.json"
        val testObserver = TestObserver<WeatherData>()
        webServer.enqueue(
            MockResponse().setBody(javaClass.classLoader.readFileFromResources(json))
        )
        repository.fetchForecastByName(ArgumentMatchers.anyString()).subscribe(testObserver)
        testObserver.assertValue {
            it.detailedForecast[0].weatherIcon == R.mipmap.few_clouds
        }
    }
}