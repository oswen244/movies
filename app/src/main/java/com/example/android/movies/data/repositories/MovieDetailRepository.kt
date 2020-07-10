package com.example.android.movies.data.repositories

import android.util.Log
import com.example.android.movies.bl.IMovieDetailRetrieve
import com.example.android.movies.data.ApiClient
import com.example.android.movies.data.OperationResult
import com.example.android.movies.models.MovieDetail
import com.example.android.movies.utils.Constants
import com.example.android.movies.utils.fromJson
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieDetailRepository: IMovieDetailRetrieve {
    override suspend fun retrieveMovieDetail(movie_id: Int): OperationResult<MovieDetail> {
        try {
            val response = ApiClient.build()?.movieDetail(movie_id)
            response?.let {
                return if(it.isSuccessful && it.body()!=null){
                    val data = Gson().toJson(it.body())
                    val movie: MovieDetail = Gson().fromJson<MovieDetail>(data.toString())

                    OperationResult.Success(movie)
                }else{
                    val message = it.body()?.status_message
                    OperationResult.Error(
                        Exception(message)
                    )
                }
            }?:run{
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