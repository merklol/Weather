package com.maximapps.weather.mainscreen.ui

import android.Manifest.permission
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.maximapps.weather.R
import com.maximapps.weather.common.utils.observe
import com.maximapps.weather.databinding.FragmentMainBinding
import com.maximapps.weather.mainscreen.usecases.common.DetailedForecast
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericFastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by activityViewModels()
    private val binding by viewBinding(FragmentMainBinding::bind)
    private val adapter = ItemAdapter<GenericItem>()
    private val fastAdapter: GenericFastAdapter = FastAdapter.with(adapter)

    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            viewModel.fetchNewForecastByLocation(isGranted)
        }

    override fun onStart() {
        super.onStart()
        viewModel.fetchForecast("Shanghai")
    }

    //region Initialization

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.weatherList.itemAnimator = null
        binding.weatherList.adapter = fastAdapter
        with(viewModel) {
            screenTitle.observe(lifecycleScope, ::showScreenTitle)
            weatherData.observe(lifecycleScope, ::showWeatherData)
            errorMessage.observe(lifecycleScope, ::showErrorMessage)
            loaderVisibility.observe(lifecycleScope, ::showProgressIndicator)
            rationaleDialogVisibility.observe(lifecycleScope) { showRationaleDialog() }
        }
        binding.toolbar.searchBtn.setOnClickListener(::showSearchDialog)
        binding.toolbar.locationBtn.setOnClickListener(::requestPermission)
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

    private fun showErrorMessage(@StringRes resId: Int) =
        Snackbar.make(requireView(), resId, Snackbar.LENGTH_SHORT).show()

    private fun showProgressIndicator(isVisible: Boolean) {
        binding.weatherList.isVisible = !isVisible
        binding.progressIndicator.isVisible = isVisible
    }

    private fun showRationaleDialog() = findNavController()
        .navigate(MainFragmentDirections.actionMainFragmentToRationaleDialog())

    @Suppress("unused_parameter")
    private fun showSearchDialog(view: View) = findNavController()
        .navigate(MainFragmentDirections.actionMainFragmentToMainDialog())

    @Suppress("unused_parameter")
    private fun requestPermission(view: View) {
        activityResultLauncher.launch(permission.ACCESS_FINE_LOCATION)
    }

    //endregion
}
