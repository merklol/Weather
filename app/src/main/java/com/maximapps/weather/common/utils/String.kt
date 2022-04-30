package com.maximapps.weather.common.utils

import java.util.Locale

/**
 * Returns a copy of this string having its first letter capitalized
 */
val String.capitalized
    get() = replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }
