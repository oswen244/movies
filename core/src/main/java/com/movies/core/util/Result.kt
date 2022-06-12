package com.movies.core.util

data class Result<T>(var status: OperationResult, var data: T? = null, val message: String?){

    sealed class OperationResult {
        object Success : OperationResult()
        data class Error(val exception:Exception?, val code: Int) : OperationResult()
    }

    companion object{
        fun<T> success(data: T?): Result<T>{
            return Result(
                status = OperationResult.Success,
                data = data,
                message = null
            )
        }

        fun<T> error(exception: Exception?, code: Int, data: T?): Result<T>{
            return Result(
                status = OperationResult.Error(exception, code),
                data = data,
                message = exception?.message
            )
        }
    }
}

