package com.example.android.movies.app.model.state

sealed class MovieListState {
    object LoadingState: MovieListState()
    object LoadingErrorState: MovieListState()
    object EmptyListErrorState: MovieListState()
}