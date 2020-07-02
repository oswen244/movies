package com.example.android.movies.injection

import com.example.android.movies.data.GenreRepository
import com.example.android.movies.models.interfaces.IMoviesGenresRetrieve
import com.example.android.movies.viewmodels.GenreViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repoModule = module {
    single<IMoviesGenresRetrieve>{GenreRepository()}
}

val viewModelModule = module {
    viewModel { GenreViewModel(get()) }
}