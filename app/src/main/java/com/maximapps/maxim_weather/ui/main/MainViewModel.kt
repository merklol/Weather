package com.maximapps.maxim_weather.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.mainScreen.domain.WeatherRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {
    private val disposables = CompositeDisposable()
    private val _liveData = MutableLiveData<MainState>()
    val liveData = _liveData

    fun getForecast(cityName: String) {
        disposables.add(
            repository.getForecast(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _liveData.value = MainState.Loading }
                .subscribe(
                    { response ->
                        _liveData.value =
                            MainState.Loaded(response.cityName, response.detailedForecast)
                    },
                    {
                        _liveData.value = MainState.Error(R.string.error_message)
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}
