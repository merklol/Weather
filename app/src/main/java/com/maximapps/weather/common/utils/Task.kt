package com.maximapps.weather.common.utils

import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Represents Task as a Flow.
 */
@OptIn(ExperimentalCoroutinesApi::class)
fun <T : Any> Task<T>.asFlow(tokenSource: CancellationTokenSource? = null): Flow<T> =
    callbackFlow {
        addOnSuccessListener(::trySend)
        addOnFailureListener(channel::close)
        awaitClose { if (isCanceled) tokenSource?.cancel() }
    }
