package com.example.android.movies.injection

import com.example.android.movies.bl.IMovieRetrieve
import com.example.android.movies.bl.IMoviesGenresRetrieve
import com.example.android.movies.data.repositories.GenreRepository
import com.example.android.movies.data.repositories.MovieListRepository
import com.example.android.movies.viewmodels.GenreViewModel
import com.example.android.movies.viewmodels.MovieListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repoModule = module {
    single<IMoviesGenresRetrieve> { GenreRepository() }
    single<IMovieRetrieve> { MovieListRepository() }
}

val viewModelModule = module {
    viewModel { GenreViewModel(get()) }
    viewModel { MovieListViewModel(get()) }
}