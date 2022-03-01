package com.maximapps.maxim_weather.mainScreen.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.common.SingleLiveEvent
import com.maximapps.maxim_weather.mainScreen.domain.WeatherRepository
import com.maximapps.maxim_weather.mainScreen.domain.models.WeatherData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {
    private var isFirstLaunch = true
    private val disposables = CompositeDisposable()

    private val _data = MutableLiveData<WeatherData>()
    val data: LiveData<WeatherData> = _data

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = SingleLiveEvent<Int>()
    val error: LiveData<Int> = _error

    fun fetchNewForecast(cityName: String) {
        disposables.add(
            repository.fetchForecast(cityName)
                .doOnSubscribe { _isLoading.postValue(true) }
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
        _isLoading.postValue(false)
        _data.postValue(data)
    }

    @Suppress("unused_parameter")
    private fun onError(throwable: Throwable) {
        _isLoading.postValue(false)
        _data.postValue(WeatherData())
        _error.postValue(R.string.error_message)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}
