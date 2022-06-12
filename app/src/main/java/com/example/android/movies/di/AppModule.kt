package com.example.android.movies.di

import com.example.android.movies.app.viewmodels.GenreViewModel
import com.example.android.movies.app.viewmodels.MovieDetailViewModel
import com.example.android.movies.app.viewmodels.MovieListViewModel
import com.example.android.movies.app.viewmodels.SearchMovieViewModel
import com.movies.core.data.datasource.MoviesRemoteDataSource
import com.movies.core.data.datasource.MoviesRemoteDataSourceImp
import com.movies.core.data.repository.MovieRepositoryImp
import com.movies.core.domain.interactor.MoviesInteractor
import com.movies.core.domain.repository.MovieRepository
import com.movies.core.support.DispatcherProvider
import com.movies.core.support.DispatcherProviderImp
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val dispatcherModule = module {
    single<DispatcherProvider> { DispatcherProviderImp() }
}

val datasourceModule = module {
    single<MoviesRemoteDataSource> { MoviesRemoteDataSourceImp(service = get(), dispatcherProvider = get()) }
}

val interactorModule = module {
    single { MoviesInteractor(moviesRepository = get()) }
}

val repoModule = module {
    single<MovieRepository> { MovieRepositoryImp(remoteDataSource =  get()) }
}

val viewModelModule = module {
    viewModel { GenreViewModel(movieInteractor =  get(), appDispatcher = get()) }
    viewModel { MovieListViewModel(moviesInteractor =  get(), appDispatcher = get()) }
    viewModel { MovieDetailViewModel(moviesInteractor =  get(), appDispatcherProvider = get()) }
    viewModel { SearchMovieViewModel(moviesInteractor =  get(), appDispatcher = get()) }
}