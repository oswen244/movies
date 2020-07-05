package com.example.android.movies.data

sealed class OperationResult<out T> {
    data class Success<T>(val data: T?) : OperationResult<T>()
    data class Error(val exception:Exception?) : OperationResult<Nothing>()
}