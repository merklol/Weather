package com.maximapps.maxim_weather.mainScreen.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.databinding.FragmentDialogMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainDialogFragment : DialogFragment(R.layout.fragment_dialog_main) {
    private val viewModel: MainViewModel by activityViewModels()
    private val binding by viewBinding(FragmentDialogMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.Theme_Weather_Dialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addBtn.setOnClickListener {
            viewModel.fetchNewForecast(binding.textInput.text.toString())
            dismiss()
        }
        binding.cancelBtn.setOnClickListener { dismiss() }
    }
}
