package com.maximapps.maxim_weather.core

interface Mapper<I, O> {
    fun map(input: I): O
}