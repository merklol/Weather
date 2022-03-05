package com.maximapps.maxim_weather.mainScreen.ui

import androidx.lifecycle.ViewModel
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.mainScreen.domain.WeatherRepository
import com.maximapps.maxim_weather.mainScreen.domain.models.DetailedForecast
import com.maximapps.maxim_weather.mainScreen.domain.models.WeatherData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {
    private var isFirstLaunch = true
    private val disposables = CompositeDisposable()

    private val _screenTitle = MutableStateFlow("")
    val screenTitle = _screenTitle.asStateFlow()

    private val _loaderVisibility = MutableStateFlow(true)
    val loaderVisibility = _loaderVisibility.asStateFlow()

    private val _weatherData = MutableStateFlow(emptyList<DetailedForecast>())
    val weatherData = _weatherData.asStateFlow()

    private val _errorMessage = MutableSharedFlow<Int>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val errorMessage = _errorMessage.asSharedFlow()

    fun fetchNewForecast(cityName: String) {
        disposables.add(
            repository.fetchForecast(cityName)
                .doOnSubscribe { _loaderVisibility.value = true }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::onSuccess, ::onError)
        )
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
