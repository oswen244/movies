package com.example.android.movies.app.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.android.movies.app.model.state.MovieListState
import com.example.android.movies.app.model.state.MovieListState.*
import com.example.android.movies.app.support.BaseViewModel
import com.example.android.movies.app.support.asLiveData
import com.movies.core.domain.entity.MovieEntity
import com.movies.core.domain.interactor.MoviesInteractor
import com.movies.core.support.DispatcherProvider
import com.movies.core.support.ResultDomain.Error
import com.movies.core.support.ResultDomain.Success

class MovieListViewModel(private val moviesInteractor: MoviesInteractor,
                         appDispatcher: DispatcherProvider
): BaseViewModel(appDispatcher) {
    private val _movieList = MutableLiveData<MutableList<MovieEntity>>().apply { value = arrayListOf() }
    val movieList = _movieList.asLiveData()

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading = _isViewLoading.asLiveData()

    private val _onMessageError=MutableLiveData<Any>()
    val onMessageError = _onMessageError.asLiveData()

    private val _isEmptyList=MutableLiveData<Boolean>()
    val isEmptyList = _isEmptyList.asLiveData()

    private val _maxPages = MutableLiveData<Int>()
    val maxPages = _maxPages.asLiveData()

    private val _currentPage = MutableLiveData<Int>()
    val currentPage = _currentPage.asLiveData()

    private val _state = MutableLiveData<MovieListState>()
    val state = _state.asLiveData()

    private fun setState(state: MovieListState){
        _state.value = state
    }

    fun loadMoviesByGenre(genreId: String, sort: String, page: Int) = execute {
        setState(LoadingState)
        when(val response = moviesInteractor.getMoviesByGenre(genreId, sort, page)){
            is Success -> {
                if(response.data.results.isNullOrEmpty()){
                    setState(EmptyListErrorState)
                }else{
                    _maxPages.postValue(response.data.total_pages)
                    _currentPage.value = response.data.page
                    _movieList.value = response.data.results as MutableList<MovieEntity>
                }
            }

            is Error -> {
                setState(LoadingErrorState)
                _onMessageError.postValue(response.error)
            }
        }
    }
}