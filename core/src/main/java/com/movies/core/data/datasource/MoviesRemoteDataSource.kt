package com.movies.core.data.datasource

import com.movies.core.data.model.GenreDto
import com.movies.core.data.model.MovieDto
import com.movies.core.data.model.MoviesDto
import com.movies.core.util.Result

interface MoviesRemoteDataSource {
    suspend fun getGenres(): Result<GenreDto>
    suspend fun getMoviesByGenre(genreId: String, sort: String, page: Int): Result<MoviesDto>
    suspend fun getMovieDetail(movie_id: Int): Result<MovieDto>
    suspend fun searchMovie(query: String?, page: Int): Result<MoviesDto>
}