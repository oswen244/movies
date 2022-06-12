package com.movies.core.support

import com.movies.core.support.ResultDomain.Error

object GenericErrorMapper : ErrorMapper(){
    override fun customError(code: Int?, errorBody: String?): Error {
        return Error(GenericError)
    }

    override fun genericError(): ErrorDomain {
        return GenericError
    }

}