package com.example.android.movies.bl

import com.example.android.movies.data.OperationResult
import com.example.android.movies.models.MovieList

interface IMovieRetrieve {
    suspend fun retrieveMovies(genreId: String): OperationResult<MovieList>
}