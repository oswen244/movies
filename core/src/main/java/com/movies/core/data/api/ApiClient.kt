package com.movies.core.data.api

import com.movies.core.data.model.GenreDto
import com.movies.core.data.model.MovieDto
import com.movies.core.data.model.MoviesDto
import com.movies.core.util.Constants.APIKEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServicesApiInterface{
    @GET("genre/movie/list${APIKEY}")
    suspend fun genres(): Response<GenreDto>

    @GET("discover/movie${APIKEY}")
    suspend fun moviesByGenre(@Query("with_genres") genre_id: String, @Query("sort_by") sort: String, @Query("page") page: Int): Response<MoviesDto>

    @GET("movie/{movie_id}${APIKEY}&append_to_response=credits")
    suspend fun movieDetail(@Path("movie_id") movie_id: Int): Response<MovieDto>

    @GET("search/movie${APIKEY}")
    suspend fun searchMovie(@Query("query") query: String?, @Query("page") page: Int): Response<MoviesDto>

}
