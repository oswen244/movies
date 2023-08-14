package com.example.android.movies.app.viewmodels

import androidx.lifecycle.MutableLiveData
import com.example.android.movies.app.model.state.GenresState
import com.example.android.movies.app.model.state.GenresState.LoadingErrorState
import com.example.android.movies.app.model.state.GenresState.LoadingState
import com.example.android.movies.app.model.state.GenresState.SuccessState
import com.example.android.movies.app.support.BaseViewModel
import com.example.android.movies.app.support.asLiveData
import com.movies.core.domain.entity.GenreEntity
import com.movies.core.domain.interactor.MoviesInteractor
import com.movies.core.support.DispatcherProvider
import com.movies.core.support.ResultDomain.Error
import com.movies.core.support.ResultDomain.Success

class GenreViewModel(private val movieInteractor: MoviesInteractor,
                     appDispatcher: DispatcherProvider
): BaseViewModel(appDispatcher) {
    private val _genreList = MutableLiveData<List<GenreEntity>>().apply { value = emptyList() }
    val genreList = _genreList.asLiveData()

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading = _isViewLoading.asLiveData()

    private val _isEmptyList=MutableLiveData<Boolean>()
    val isEmptyList = _isEmptyList.asLiveData()

    private val _state = MutableLiveData<GenresState>()
    val state = _state.asLiveData()

    private fun setState(state: GenresState){
        _state.value = state
    }

    fun loadMovieGenres() = execute {
        setState(LoadingState)
        when(val response = movieInteractor.getGenres()){
            is Success -> {
                if(response.data.isEmpty()){
                    _isEmptyList.postValue(true)
                    setState(LoadingErrorState)
                }else{
                    _genreList.value = response.data
                    setState(SuccessState)
                }
            }

            is Error -> {
                setState(LoadingErrorState)
            }
        }
    }
}