package com.maximapps.weather.common.utils

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

fun <T> Flow<T>.observe(lifecycleScope: LifecycleCoroutineScope, action: (value: T) -> Unit) {
    lifecycleScope.launchWhenCreated {
        this@observe.collect(action)
    }
}
