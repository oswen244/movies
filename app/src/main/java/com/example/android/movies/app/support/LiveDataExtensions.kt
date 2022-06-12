package com.example.android.movies.app.support

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>

fun <T> MutableLiveData<T>.reduce(async: Boolean = false, reducer: T.() -> T){
    value?.let {
        if(async){
            postValue(it.reducer())
        }else{
            value = it.reducer()
        }
    }
}