package com.maximapps.maxim_weather.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.core.BaseFragment
import com.maximapps.maxim_weather.databinding.FragmentMainBinding
import com.maximapps.maxim_weather.di.viewmodels.ViewModelFactory
import com.maximapps.maxim_weather.ui.lists.weatherListAdapter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MainFragment : BaseFragment(R.layout.fragment_main) {
    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: MainViewModel by viewModels { factory }
    private val binding by viewBinding(FragmentMainBinding::bind)

    private val adapter by lazy {
        weatherListAdapter(onItemClick = {
            findNavController().navigate(
                com.maximapps.maxim_weather.ui.MainFragmentDirections.actionMainFragmentToDetailsFragment(
                    it
                )
            )
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getForecast()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.weatherList.adapter = adapter
        viewModel.liveData.observe(viewLifecycleOwner, ::render)
    }

    private fun render(state: MainState) {
        when (state) {
            is MainState.Loading -> {
                binding.progressIndicator.isVisible = true
            }
            is MainState.Success -> {
                binding.progressIndicator.isVisible = false
                adapter.setData(state.response.forecastList)
                setToolbarTitle(state.response.cityName)
            }
            is MainState.Fail -> {
                binding.progressIndicator.isVisible = false
                Snackbar.make(requireView(), state.errorMessage, Snackbar.LENGTH_SHORT).show()
            }

        }
    }
}