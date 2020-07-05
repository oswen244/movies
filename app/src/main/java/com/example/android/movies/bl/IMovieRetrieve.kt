package com.example.android.movies.bl

import com.example.android.movies.data.OperationResult
import com.example.android.movies.data.OperationResultList
import com.example.android.movies.models.MovieList
import com.example.android.movies.models.MovieListByGenreResponse

interface IMovieRetrieve {
    suspend fun retrieveMovies(genreId: String, page: Int): OperationResult<MovieListByGenreResponse>
}