package com.example.android.movies.app.model.state

sealed class GenresState{
    object LoadingState: GenresState()
    object LoadingErrorState: GenresState()
    object SuccessState: GenresState()
}
