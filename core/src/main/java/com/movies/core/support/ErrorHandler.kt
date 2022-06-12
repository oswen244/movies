package com.movies.core.support

import com.movies.core.util.Result

suspend fun <T, O> responseErrorHandler(
    mapper: ErrorMapper,
    response: Result<T>,
    mapAction: suspend (T) -> ResultDomain<O>
): ResultDomain<O> = when(val statusResponse = response.status){
    is Result.OperationResult.Success -> response.data?.let { mapAction(it) } ?: ResultDomain.Error(mapper.genericError())
    is Result.OperationResult.Error -> mapper.customError(statusResponse.code, statusResponse.exception?.message)
}