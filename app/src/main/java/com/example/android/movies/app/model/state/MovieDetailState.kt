package com.example.android.movies.app.model.state

sealed class MovieDetailState {
    object LoadingMovieData: MovieDetailState()
    object LoadingErrorMovieData: MovieDetailState()
}