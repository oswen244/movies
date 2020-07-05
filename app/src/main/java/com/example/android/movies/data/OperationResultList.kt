package com.example.android.movies.data

sealed class OperationResultList<out T> {
    data class Success<T>(val data: List<T>?) : OperationResultList<T>()
    data class Error(val exception:Exception?) : OperationResultList<Nothing>()
}