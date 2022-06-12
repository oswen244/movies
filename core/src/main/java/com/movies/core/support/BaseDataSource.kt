package com.movies.core.support

import kotlinx.coroutines.withContext
import retrofit2.Response
import com.movies.core.util.Result
import java.lang.Exception

abstract class BaseDataSource(var dispatcherProvider: DispatcherProvider) {

    protected suspend fun <T> executeNetworkAction(action: suspend () -> T): T =
        withContext(dispatcherProvider.network) {action()}


    protected inline fun <reified T> getResult(call: () -> Response<T>): Result<T>{
        var code = 0
        try {
            val response = call()
            code = response.code()
            if(response.isSuccessful){
                val body = response.body()
                if(body != null) return Result.success(body)
            }
            return error(
                Exception(response.message()),
                code,
                response.body()
            )
        }catch (e: Exception){
            return error(e, code)
        }
    }

    fun <T> error (e: Exception, code: Int, data: T? = null): Result<T> {
        return Result.error(e, code, data)
    }
}