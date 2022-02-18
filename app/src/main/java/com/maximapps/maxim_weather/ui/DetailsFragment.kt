package com.maximapps.maxim_weather.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.core.BaseFragment
import com.maximapps.maxim_weather.databinding.FragmentDetailsBinding
import com.maximapps.maxim_weather.ext.toFormattedDate

class DetailsFragment : BaseFragment(R.layout.fragment_details) {
    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(args.forecast) {
            binding.date.text = date.toFormattedDate()
            binding.weatherIcon.setImageResource(iconResId)
            binding.temperature.text = getString(R.string.temperature, temperature)
            binding.details.text = getString(R.string.today_details, weather, feelsLike)
        }
    }
}
