package com.example.android.movies.data.repositories

import android.util.Log
import com.example.android.movies.bl.IMovieRetrieve
import com.example.android.movies.data.ApiClient
import com.example.android.movies.data.OperationResultList
import com.example.android.movies.models.MovieList
import java.lang.Exception

class MovieListRepository: IMovieRetrieve {

    override suspend fun retrieveMovies(genreId: String): OperationResultList<MovieList> {
        try {
            val response = ApiClient.build()?.moviesByGenre(genreId)
            response?.let {
                return if (it.isSuccessful && it.body() != null) {
                    val data = it.body()?.results
                    OperationResultList.Success(data)
                } else {
                    val message = it.body()?.status_message
                    OperationResultList.Error(
                        Exception(message)
                    )
                }
            } ?: run {
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