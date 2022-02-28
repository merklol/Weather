package com.maximapps.maxim_weather.common

/**
 * Interface for model mappers.
 */
interface Mapper<I, O> {
    fun map(input: I): O
}
