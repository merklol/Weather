package com.maximapps.maxim_weather.mainScreen.ui

import androidx.lifecycle.ViewModel
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.common.MutableSingleEventFlow
import com.maximapps.maxim_weather.mainScreen.usecases.common.DetailedForecast
import com.maximapps.maxim_weather.mainScreen.usecases.common.WeatherData
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates.FETCH_FORECAST_BY_COORDINATES
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbycoordinates.FetchForecastByCoordinates
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbyname.FETCH_FORECAST_BY_NAME
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbyname.FetchForecastByName
import com.maximapps.maxim_weather.mainScreen.usecases.fetchforecastbyname.FetchForecastByNameImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Named

class MainViewModel @Inject constructor(
    @Named(FETCH_FORECAST_BY_NAME)
    private val fetchForecastByName: FetchForecastByName,
    @Named(FETCH_FORECAST_BY_COORDINATES)
    private val fetchForecastByCoordinates: FetchForecastByCoordinates
) : ViewModel() {
    private var isFirstLaunch = true
    private val disposables = CompositeDisposable()

    private val _screenTitle = MutableStateFlow("")
    val screenTitle = _screenTitle.asStateFlow()

    private val _loaderVisibility = MutableStateFlow(false)
    val loaderVisibility = _loaderVisibility.asStateFlow()

    private val _weatherData = MutableStateFlow(emptyList<DetailedForecast>())
    val weatherData = _weatherData.asStateFlow()

    private val _errorMessage = MutableSingleEventFlow<Int>()
    val errorMessage = _errorMessage.asSharedFlow()

    private val _rationaleDialogVisibility = MutableSingleEventFlow<Unit>()
    val rationaleDialogVisibility = _rationaleDialogVisibility.asSharedFlow()


    fun fetchNewForecastByLocation(isGranted: Boolean) {
        if (!isGranted) {
            _rationaleDialogVisibility.tryEmit(Unit)
            return
        }
        fetchForecastByCoordinates()
            .doOnSubscribe { _loaderVisibility.value = true }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .also { disposables.add(it) }
    }

    fun fetchNewForecast(cityName: String) {
        fetchForecastByName(FetchForecastByNameImpl.Params(cityName))
            .doOnSubscribe { _loaderVisibility.value = true }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
            .also { disposables.add(it) }
    }

    fun fetchForecast(cityName: String) {
        if (isFirstLaunch) {
            fetchNewForecast(cityName)
            isFirstLaunch = false
        }
    }

    private fun onSuccess(data: WeatherData) {
        _loaderVisibility.value = false
        _screenTitle.value = data.cityName
        _weatherData.value = data.detailedForecast
    }

    @Suppress("unused_parameter")
    private fun onError(throwable: Throwable) {
        _screenTitle.value = ""
        _weatherData.value = emptyList()
        _loaderVisibility.value = false
        _errorMessage.tryEmit(R.string.error_message)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}
