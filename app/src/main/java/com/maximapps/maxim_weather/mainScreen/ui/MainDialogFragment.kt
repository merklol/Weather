package com.maximapps.maxim_weather.mainScreen.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maximapps.maxim_weather.R
import com.maximapps.maxim_weather.databinding.FragmentDialogMainBinding
import com.maximapps.maxim_weather.common.di.factory.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MainDialogFragment : DialogFragment(R.layout.fragment_dialog_main) {
    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: MainViewModel by activityViewModels { factory }

    private val binding by viewBinding(FragmentDialogMainBinding::bind)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.Theme_Maxim_weather_Dialog)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addBtn.setOnClickListener {
            viewModel.getForecast(binding.textInput.text.toString())
            dismiss()
        }
        binding.cancelBtn.setOnClickListener { dismiss() }
    }
}
