package com.movies.core.support

import com.movies.core.support.ResultDomain.Error

abstract class ErrorMapper {
    abstract fun customError(code: Int?, errorBody: String?): Error
    abstract fun genericError(): ErrorDomain
}