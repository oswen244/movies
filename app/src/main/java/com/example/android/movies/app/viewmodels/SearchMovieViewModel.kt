package com.example.android.movies.app.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.android.movies.app.model.state.MovieListState
import com.example.android.movies.app.support.BaseViewModel
import com.example.android.movies.app.support.asLiveData
import com.movies.core.domain.entity.MovieEntity
import com.movies.core.domain.interactor.MoviesInteractor
import com.movies.core.support.DispatcherProvider
import com.movies.core.support.ResultDomain

class SearchMovieViewModel(private val moviesInteractor: MoviesInteractor,
                           appDispatcher: DispatcherProvider
): BaseViewModel(appDispatcher) {
    private val _moviesResult = MutableLiveData<MutableList<MovieEntity>>().apply { value = arrayListOf() }
    val movieResult: MutableLiveData<MutableList<MovieEntity>> = _moviesResult

    private val _maxPages = MutableLiveData<Int>()
    val maxPages = _maxPages.asLiveData()

    private val _currentPage = MutableLiveData<Int>()
    val currentPage = _currentPage.asLiveData()

    private val _isEmptyList=MutableLiveData<Boolean>()
    val isEmptyList = _isEmptyList.asLiveData()

    private val _onMessageError=MutableLiveData<Any>()
    val onMessageError = _onMessageError.asLiveData()

    private val _state = MutableLiveData<MovieListState>()
    val state = _state.asLiveData()

    private fun setState(state: MovieListState){
        _state.value = state
    }

    fun searchMovie(query: String?, page: Int) = execute{
        setState(MovieListState.LoadingState)
        when(val response = moviesInteractor.searchMovie(query, page)){
            is ResultDomain.Success -> {
                if(response.data.results.isNullOrEmpty()){
                    _isEmptyList.postValue(true)
                }else{
                    _currentPage.value = response.data.page
                    _maxPages.postValue(response.data.total_pages)
                    _moviesResult.value = response.data.results as MutableList<MovieEntity>
                }
            }

            is ResultDomain.Error -> {
                _onMessageError.postValue(response.error)
            }
        }
    }

}