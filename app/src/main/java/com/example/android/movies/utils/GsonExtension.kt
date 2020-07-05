package com.example.android.movies.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Gson class extension to convert json to generic class
 */
inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)