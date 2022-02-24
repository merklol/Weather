package com.maximapps.maxim_weather.utils

import java.io.IOException

@Throws(IOException::class)
fun ClassLoader?.readFileFromResources(fileName: String): String =
    this?.getResourceAsStream(fileName)?.bufferedReader()
        .use { bufferReader -> bufferReader?.readText() } ?: ""