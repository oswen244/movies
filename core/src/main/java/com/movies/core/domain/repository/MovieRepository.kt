package com.movies.core.domain.repository

import com.movies.core.domain.entity.GenreEntity
import com.movies.core.domain.entity.MovieEntity
import com.movies.core.domain.entity.MovieListEntity
import com.movies.core.support.ResultDomain

interface MovieRepository {
    suspend fun getGenres(): ResultDomain<List<GenreEntity>>
    suspend fun getMoviesByGenre(genreId: String, sort: String, page: Int): ResultDomain<MovieListEntity>
    suspend fun getMovieDetail(movie_id: Int): ResultDomain<MovieEntity>
    suspend fun searchMovie(query: String?, page: Int): ResultDomain<MovieListEntity>
}