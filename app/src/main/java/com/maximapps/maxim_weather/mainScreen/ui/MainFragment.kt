package com.maximapps.maxim_weather.mainScreen.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.databinding.FragmentMainBinding
import com.maximapps.maxim_weather.common.di.factory.ViewModelFactory
import com.maximapps.maxim_weather.mainScreen.domain.models.DetailedForecast
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {
    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: MainViewModel by activityViewModels { factory }
    private val binding by viewBinding(FragmentMainBinding::bind)
    private val adapter = weatherListAdapter(onItemClick = { })

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getForecast("Shanghai")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.weatherList.adapter = adapter
        binding.toolbar.searchBtn.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToMainDialog())
        }
        viewModel.liveData.observe(viewLifecycleOwner) {
            when (it) {
                is MainState.Loading -> showProgressIndicator()
                is MainState.Loaded -> showWeather(it.cityName, it.detailedForecast)
                is MainState.Error -> showError(it.resId)
            }
        }
    }

    private fun showProgressIndicator() {
        binding.weatherList.isVisible = false
        binding.progressIndicator.isVisible = true
    }

    private fun showWeather(cityName: String, detailedForecast: List<DetailedForecast>) {
        binding.weatherList.isVisible = true
        binding.progressIndicator.isVisible = false
        binding.toolbar.title.text = cityName
        adapter.setData(detailedForecast)
    }

    private fun showError(@StringRes resId: Int) {
        binding.progressIndicator.isVisible = false
        Snackbar.make(requireView(), resId, Snackbar.LENGTH_SHORT).show()
    }
}
