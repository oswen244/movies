package com.movies.core.data.repository

import com.movies.core.data.datasource.MoviesRemoteDataSource
import com.movies.core.domain.entity.GenreEntity
import com.movies.core.domain.entity.MovieEntity
import com.movies.core.domain.entity.MovieListEntity
import com.movies.core.domain.entity.mapToDomain
import com.movies.core.domain.repository.MovieRepository
import com.movies.core.support.GenericErrorMapper
import com.movies.core.support.ResultDomain
import com.movies.core.support.responseErrorHandler

class MovieRepositoryImp(var remoteDataSource: MoviesRemoteDataSource): MovieRepository {
    override suspend fun getGenres(): ResultDomain<List<GenreEntity>> = responseErrorHandler(
        GenericErrorMapper,
        remoteDataSource.getGenres()
    ) { ResultDomain.Success(it.genres) }

    override suspend fun getMoviesByGenre(genreId: String, sort: String, page: Int): ResultDomain<MovieListEntity> = responseErrorHandler(
        GenericErrorMapper,
        remoteDataSource.getMoviesByGenre(genreId, sort, page),
    ) { ResultDomain.Success(it.mapToDomain()) }

    override suspend fun getMovieDetail(movie_id: Int): ResultDomain<MovieEntity> = responseErrorHandler(
        GenericErrorMapper,
        remoteDataSource.getMovieDetail(movie_id)
    ) { ResultDomain.Success(it.mapToDomain()) }

    override suspend fun searchMovie(query: String?, page: Int): ResultDomain<MovieListEntity> = responseErrorHandler(
        GenericErrorMapper,
        remoteDataSource.searchMovie(query, page)
    ) { ResultDomain.Success(it.mapToDomain()) }
}