package com.example.android.movies.data.repositories

import android.util.Log
import com.example.android.movies.data.ApiClient
import com.example.android.movies.data.OperationResultList
import com.example.android.movies.models.Genre
import com.example.android.movies.bl.IMoviesGenresRetrieve
import java.lang.Exception

class GenreRepository: IMoviesGenresRetrieve {
    override suspend fun retrieveMuseums(): OperationResultList<Genre> {
        try {
            val response = ApiClient.build()?.genres()
            response?.let {
                return if(it.isSuccessful && it.body()!=null){
                    val data = it.body()?.genres
                    OperationResultList.Success(data)
                }else{
                    val message = it.body()?.status_message
                    OperationResultList.Error(
                        Exception(message)
                    )
                }
            }?:run{
                return OperationResultList.Error(
                    Exception("Ocurrió un error")
                )
            }
        }catch (e: Exception){
            Log.e("ExceptionRepo", e.message)
            return OperationResultList.Error(e)
        }
    }
}