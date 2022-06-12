package com.example.android.movies.app.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.android.movies.app.model.state.MovieDetailState
import com.example.android.movies.app.model.state.MovieDetailState.LoadingErrorMovieData
import com.example.android.movies.app.model.state.MovieDetailState.LoadingMovieData
import com.example.android.movies.app.support.BaseViewModel
import com.example.android.movies.app.support.asLiveData
import com.example.android.movies.utils.Constants
import com.movies.core.domain.entity.MovieCastEntity
import com.movies.core.domain.entity.MovieEntity
import com.movies.core.domain.entity.ProductionCompanyEntity
import com.movies.core.domain.interactor.MoviesInteractor
import com.movies.core.support.DispatcherProvider
import com.movies.core.support.ResultDomain.Error
import com.movies.core.support.ResultDomain.Success

class MovieDetailViewModel(
    private val moviesInteractor: MoviesInteractor, appDispatcherProvider: DispatcherProvider
) : BaseViewModel(appDispatcherProvider) {

    private val _movieDetail = MutableLiveData<MovieEntity>()
    val movieDetail = _movieDetail.asLiveData()

    private val _movieProducers = MutableLiveData<List<ProductionCompanyEntity>>().apply { value = emptyList() }
    val movieProducers = _movieProducers.asLiveData()

    private val _movieCast = MutableLiveData<List<MovieCastEntity>>().apply { value = emptyList() }
    val movieCast = _movieCast.asLiveData()

    private val _movieImage = MutableLiveData<String>()
    val movieImage = _movieImage.asLiveData()

    private val _movieOverview = MutableLiveData<String>()
    val movieOverview = _movieOverview.asLiveData()

    private val _movieRate = MutableLiveData<String>()
    val movieRate = _movieRate.asLiveData()

    private val _movieReleaseDate = MutableLiveData<String>()
    val movieReleaseDate = _movieReleaseDate.asLiveData()

    private val _urlHomepage = MutableLiveData<String>()
    val urlHomepage = _urlHomepage.asLiveData()

    private val _state = MutableLiveData<MovieDetailState>()
    val state = _state.asLiveData()

    private fun setState(state: MovieDetailState){
        _state.value = state
    }

    fun loadMovieDetail(movieId: Int) = execute {
        when(val response = moviesInteractor.getMovieDetail(movieId)){
            is Success -> {
                bind(response.data)
            }

            is Error -> {
                setState(LoadingErrorMovieData)
            }
        }
    }

    private fun bind(movie: MovieEntity){
        _movieImage.postValue(Constants.IMAGES+movie.backdrop_path)
        _movieOverview.postValue(movie.overview)
        _movieRate.postValue(movie.vote_average.toString())
        _movieReleaseDate.postValue(movie.release_date)
        _movieProducers.postValue(movie.production_companies)
        _movieCast.postValue(movie.credits?.cast)
        _urlHomepage.postValue(movie.homepage)
    }
}