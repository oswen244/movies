
package com.example.android.movies.utils

import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    SDK_INT >= TIRAMISU -> getParcelableExtra(key, T::class.java)
    else ->
        @Suppress("DEPRECATION")
        getParcelableExtra(key) as? T
}