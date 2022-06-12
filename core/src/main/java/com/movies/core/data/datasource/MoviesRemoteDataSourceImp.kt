package com.movies.core.data.datasource

import com.movies.core.data.api.ServicesApiInterface
import com.movies.core.data.model.GenreDto
import com.movies.core.data.model.MovieDto
import com.movies.core.data.model.MoviesDto
import com.movies.core.support.BaseDataSource
import com.movies.core.support.DispatcherProvider
import com.movies.core.util.Result

class MoviesRemoteDataSourceImp(var service: ServicesApiInterface, dispatcherProvider: DispatcherProvider):
    MoviesRemoteDataSource, BaseDataSource(dispatcherProvider) {

    override suspend fun getGenres(): Result<GenreDto> = getResult {
        executeNetworkAction { service.genres() }
    }

    override suspend fun getMoviesByGenre(
        genreId: String,
        sort: String,
        page: Int
    ): Result<MoviesDto> = getResult {
        executeNetworkAction {
            service.moviesByGenre(genreId, sort, page)
        }
    }

    override suspend fun getMovieDetail(movie_id: Int): Result<MovieDto> = getResult {
        executeNetworkAction { service.movieDetail(movie_id) }
    }

    override suspend fun searchMovie(query: String?, page: Int): Result<MoviesDto> = getResult {
        executeNetworkAction { service.searchMovie(query, page) }
    }
}