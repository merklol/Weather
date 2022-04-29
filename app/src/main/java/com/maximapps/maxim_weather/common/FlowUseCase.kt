package com.maximapps.maxim_weather.common

import kotlinx.coroutines.flow.Flow

/**
 * Interface for UseCases that returns an instance of a [Flow].
 */
interface FlowUseCase<P, R : Any> {
    operator fun invoke(params: P? = null): Flow<R>
}
