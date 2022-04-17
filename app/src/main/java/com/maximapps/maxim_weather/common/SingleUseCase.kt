package com.maximapps.maxim_weather.common

import io.reactivex.rxjava3.core.Single

/**
 * Interface for UseCases that returns an instance of a [Single].
 */
interface SingleUseCase<P, R : Any> {
    operator fun invoke(params: P? = null): Single<R>
}
