package com.example.android.movies.app.model.state

import com.movies.core.domain.entity.GenreEntity

sealed class GenresState{
    object LoadingErrorState: GenresState()
    object SuccessEmptyList: GenresState()
}

data class GenreViewState(
    val loading: Boolean = false,
    val genreList: List<GenreEntity> = listOf()
)
