package com.example.android.movies.bl

import com.example.android.movies.data.OperationResult
import com.example.android.movies.models.MovieListByGenreResponse

interface IMovieSearchRetrieve {
    suspend fun retrieveMovieSearch(query: String?, page: Int): OperationResult<MovieListByGenreResponse>
}