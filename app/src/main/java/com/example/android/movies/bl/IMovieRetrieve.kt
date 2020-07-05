package com.example.android.movies.bl

import com.example.android.movies.data.OperationResultList
import com.example.android.movies.models.MovieList

interface IMovieRetrieve {
    suspend fun retrieveMovies(genreId: String): OperationResultList<MovieList>
}