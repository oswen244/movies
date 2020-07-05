package com.example.android.movies.bl

import com.example.android.movies.data.OperationResult
import com.example.android.movies.models.MovieDetail

interface IMovieDetailRetrieve {
    suspend fun retrieveMovieDetail(movie_id: Int): OperationResult<MovieDetail>
}