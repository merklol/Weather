package com.maximapps.maxim_weather.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.maximapps.maxim_weather.databinding.FragmentMainBinding
import com.maximapps.maxim_weather.network.WeatherService
import com.maximapps.maxim_weather.ui.list.ListAdapter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MainFragment: Fragment() {
    @Inject
    lateinit var service: WeatherService
    private val viewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory(service)
    }
    private var binding: FragmentMainBinding? = null
    private val adapter = ListAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.weatherList?.adapter = adapter
        viewModel.getForecast()
        viewModel.liveData.observe(viewLifecycleOwner) {
            adapter.setData(it.forecastList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}