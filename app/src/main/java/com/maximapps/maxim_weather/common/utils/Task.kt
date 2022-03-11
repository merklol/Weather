package com.maximapps.maxim_weather.common.utils

import com.google.android.gms.tasks.Task
import io.reactivex.rxjava3.core.Single

/**
 * Represents Task as a Single.
 */
fun <T : Any> Task<T>.asSingle(): Single<T> = Single.create { emitter ->
    addOnSuccessListener(emitter::onSuccess)
    addOnFailureListener(emitter::tryOnError)
}
