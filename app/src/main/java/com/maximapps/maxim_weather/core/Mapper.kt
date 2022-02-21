package com.maximapps.maxim_weather.core

/**
 * Interface for model mappers.
 */
interface Mapper<I, O> {
    fun map(input: I): O
}
