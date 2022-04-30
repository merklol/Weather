package com.maximapps.weather.common

/**
 * Interface for model mappers.
 */
interface Mapper<I, O> {
    fun map(input: I): O
}
