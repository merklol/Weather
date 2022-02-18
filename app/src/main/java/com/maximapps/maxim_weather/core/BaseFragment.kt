package com.maximapps.maxim_weather.core

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.maximapps.maxim_weather.HostActivity
import dagger.android.support.AndroidSupportInjection

open class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    fun setToolbarTitle(value: String) {
        (requireActivity() as HostActivity).supportActionBar?.title = value
    }
}
