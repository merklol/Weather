package com.maximapps.maxim_weather.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maximapps.maxim_weather.network.WeatherService
import com.maximapps.maxim_weather.network.models.Response
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel(
    private val service: WeatherService
) : ViewModel() {
    private val disposables = CompositeDisposable()
    private val _liveData = MutableLiveData<Response>()
    val liveData = _liveData

    fun getForecast() {
        service.getForecast()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _liveData.value = it }, { println("Something went wrong...") }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    @Suppress("unchecked_cast")
    class MainViewModelFactory @Inject constructor(
        private val service: WeatherService
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) = MainViewModel(service) as T
    }
}