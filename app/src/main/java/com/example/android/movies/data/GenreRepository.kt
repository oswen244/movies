package com.example.android.movies.data

import android.util.Log
import com.example.android.movies.models.Genre
import com.example.android.movies.models.interfaces.IMoviesGenresRetrieve
import java.lang.Exception

class GenreRepository: IMoviesGenresRetrieve {
    override suspend fun retrieveMuseums(): OperationResult<Genre> {
        try {
            val response = ApiClient.build()?.genres()
            response?.let {
                return if(it.isSuccessful && it.body()!=null){
                    val data = it.body()?.genres
                    OperationResult.Success(data)
                }else{
                    val message = it.body()?.status_message
                    OperationResult.Error(Exception(message))
                }
            }?:run{
                return OperationResult.Error(Exception("Ocurrió un error"))
            }
        }catch (e: Exception){
            Log.e("ExceptionRepo", e.message)
            return OperationResult.Error(e)
        }
    }
}