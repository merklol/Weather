package com.maximapps.weather.common

/**
 * Weather condition codes
 *
 * @see <a href="https://openweathermap.org/weather-conditions">Weather Conditions</a>
 */
object IconTypes {
    object Day {
        const val ClearSky = "01d"
        const val FewClouds = "02d"
        const val ScatteredClouds = "03d"
        const val BrokenClouds = "04d"
        const val ShowerRain = "09d"
        const val Rain = "10d"
        const val Thunderstorm = "11d"
        const val Snow = "13d"
        const val Mist = "50d"
    }

    object Night {
        const val ClearSky = "01n"
        const val FewClouds = "02n"
        const val ScatteredClouds = "03n"
        const val BrokenClouds = "04n"
        const val ShowerRain = "09n"
        const val Rain = "10n"
        const val Thunderstorm = "11n"
        const val Snow = "13n"
        const val Mist = "50n"
    }
}