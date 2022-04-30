package com.maximapps.weather.utils

import com.google.gson.Gson
import java.io.IOException

@Throws(IOException::class)
fun <T> Class<T>.readFileFromResources(fileName: String): String =
    this.classLoader?.getResourceAsStream(fileName)?.bufferedReader()
        .use { bufferReader -> bufferReader?.readText() } ?: ""

fun <T1, T2> Class<T1>.readJsonFileToObject(fileName: String, clazz: Class<T2>): T2 =
    Gson().fromJson(readFileFromResources(fileName), clazz)