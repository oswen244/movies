package com.movies.core.domain.interactor

import com.movies.core.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class MoviesInteractor(private val moviesRepository: MovieRepository): CoroutineScope by MainScope(){

    suspend fun getGenres() =
        moviesRepository.getGenres()

    suspend fun getMoviesByGenre(genreId: String, sort: String, page: Int) =
        moviesRepository.getMoviesByGenre(genreId, sort, page)

    suspend fun getMovieDetail(movie_id: Int) =
        moviesRepository.getMovieDetail(movie_id)

    suspend fun searchMovie(query: String?, page: Int) =
        moviesRepository.searchMovie(query, page)
}