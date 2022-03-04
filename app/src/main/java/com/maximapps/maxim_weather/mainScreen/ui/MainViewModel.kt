package com.maximapps.maxim_weather.mainScreen.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.common.SingleLiveEvent
import com.maximapps.maxim_weather.mainScreen.domain.WeatherRepository
import com.maximapps.maxim_weather.mainScreen.domain.models.DetailedForecast
import com.maximapps.maxim_weather.mainScreen.domain.models.WeatherData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {
    private var isFirstLaunch = true
    private val disposables = CompositeDisposable()

    private val _screenTitle = MutableLiveData<String>()
    val screenTitle: LiveData<String> = _screenTitle

    private val _loaderVisibility = MutableLiveData<Boolean>()
    val loaderVisibility: LiveData<Boolean> = _loaderVisibility

    private val _weatherData = MutableLiveData<List<DetailedForecast>>()
    val weatherData: LiveData<List<DetailedForecast>> = _weatherData

    private val _errorMessage = SingleLiveEvent<Int>()
    val errorMessage: LiveData<Int> = _errorMessage

    fun fetchNewForecast(cityName: String) {
        disposables.add(
            repository.fetchForecast(cityName)
                .doOnSubscribe { _loaderVisibility.postValue(true) }
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
        _loaderVisibility.postValue(false)
        _screenTitle.postValue(data.cityName)
        _weatherData.postValue(data.detailedForecast)
    }

    @Suppress("unused_parameter")
    private fun onError(throwable: Throwable) {
        _screenTitle.postValue("")
        _loaderVisibility.postValue(false)
        _weatherData.postValue(emptyList())
        _errorMessage.postValue(R.string.error_message)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}
