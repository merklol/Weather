package com.maximapps.maxim_weather.mainScreen.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.common.di.factory.ViewModelFactory
import com.maximapps.maxim_weather.common.utils.observe
import com.maximapps.maxim_weather.databinding.FragmentMainBinding
import com.maximapps.maxim_weather.mainScreen.domain.models.DetailedForecast
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericFastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {
    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: MainViewModel by activityViewModels { factory }
    private val binding by viewBinding(FragmentMainBinding::bind)

    private val adapter = ItemAdapter<GenericItem>()
    private val fastAdapter: GenericFastAdapter = FastAdapter.with(adapter)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchForecast("Shanghai")
        binding.weatherList.itemAnimator = null
        binding.weatherList.adapter = fastAdapter
        binding.toolbar.searchBtn.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToMainDialog())
        }

        with(viewModel) {
            screenTitle.observe(lifecycleScope, ::showScreenTitle)
            weatherData.observe(lifecycleScope, ::showWeatherData)
            errorMessage.observe(lifecycleScope, ::showErrorMessage)
            loaderVisibility.observe(lifecycleScope, ::showProgressIndicator)
        }
    }

    private fun showScreenTitle(title: String) {
        binding.toolbar.title.text = title
    }

    private fun showWeatherData(data: List<DetailedForecast>) {
        FastAdapterDiffUtil[adapter] = data.mapIndexed { index, detailedForecast ->
            if (index == 0) {
                TodayForecastItem(detailedForecast)
            } else {
                DetailedForecastItem(detailedForecast)
            }
        }
    }

    private fun showErrorMessage(@StringRes resId: Int) {
        Snackbar.make(requireView(), resId, Snackbar.LENGTH_SHORT).show()
    }

    private fun showProgressIndicator(isVisible: Boolean) {
        binding.weatherList.isVisible = !isVisible
        binding.progressIndicator.isVisible = isVisible
    }
}
