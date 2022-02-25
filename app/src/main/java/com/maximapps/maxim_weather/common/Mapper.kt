package com.maximapps.maxim_weather.common.mappers

/**
 * Interface for model mappers.
 */
interface Mapper<I, O> {
    fun map(input: I): O
}
