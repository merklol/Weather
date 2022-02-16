package com.maximapps.maxim_weather.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.databinding.FragmentMainBinding
import com.maximapps.maxim_weather.network.WeatherService
import com.maximapps.maxim_weather.ui.list.ListAdapter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MainFragment : Fragment() {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getForecast()
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
        adapter.setOnItemClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_detailsFragment)
        }

        viewModel.liveData.observe(viewLifecycleOwner, ::render)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun render(state: MainState) {
        when (state) {
            is MainState.Loading -> {
                binding?.progressIndicator?.isVisible = true
            }
            is MainState.Success -> {
                binding?.progressIndicator?.isVisible = false
                adapter.setData(state.response.forecastList)
            }
            is MainState.Fail -> {
                binding?.progressIndicator?.isVisible = false
                Snackbar.make(requireView(), state.errorMessage, Snackbar.LENGTH_SHORT).show()
            }

        }
    }
}