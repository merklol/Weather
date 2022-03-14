package com.maximapps.maxim_weather.common.utils

import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Cancellable
import java.lang.IllegalStateException

/**
 * Represents Task as a Single.
 */
fun <T : Any> Task<T>.asSingle(tokenSource: CancellationTokenSource? = null): Single<T> =
    Single.create { emitter ->
        addOnSuccessListener(emitter::onSuccess)
        addOnFailureListener(emitter::tryOnError)
        emitter.setCancellable { tokenSource?.cancel() }
    }
