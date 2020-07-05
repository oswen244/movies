package com.example.android.movies.data.repositories

import android.util.Log
import com.example.android.movies.bl.IMovieRetrieve
import com.example.android.movies.data.ApiClient
import com.example.android.movies.data.OperationResult
import com.example.android.movies.data.OperationResultList
import com.example.android.movies.models.MovieList
import com.example.android.movies.models.MovieListByGenreResponse
import java.lang.Exception

class MovieListRepository: IMovieRetrieve {

    override suspend fun retrieveMovies(genreId: String, page: Int): OperationResult<MovieListByGenreResponse> {
        try {
            val response = ApiClient.build()?.moviesByGenre(genreId, page)
            response?.let {
                return if (it.isSuccessful && it.body() != null) {
                    val data = it.body()
                    OperationResult.Success(data)
                } else {
                    val message = it.body()?.status_message
                    OperationResult.Error(
                        Exception(message)
                    )
                }
            } ?: run {
                return OperationResult.Error(
                    Exception("Ocurrió un error")
                )
            }
        }catch (e: Exception){
            Log.e("ExceptionRepo", e.message)
            return OperationResult.Error(e)
        }
    }

}