package com.example.android.movies.app.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import com.example.android.movies.app.model.state.GenresState
import com.example.android.movies.app.model.state.GenresState.LoadingErrorState
import com.example.android.movies.app.base.BaseViewModel
import com.example.android.movies.app.model.state.GenreViewState
import com.example.android.movies.utils.asLiveData
import com.movies.core.domain.entity.GenreEntity
import com.movies.core.domain.interactor.MoviesInteractor
import com.movies.core.support.DispatcherProvider
import com.movies.core.support.ResultDomain.Error
import com.movies.core.support.ResultDomain.Success

class GenreViewModel(private val movieInteractor: MoviesInteractor,
                     appDispatcher: DispatcherProvider
): BaseViewModel(appDispatcher) {

    private val _state = MutableLiveData<GenresState>()
    val state = _state.asLiveData()

    var viewState by mutableStateOf(GenreViewState())
        private set

    private fun setState(state: GenresState){
        _state.value = state
    }

    private fun setList(list: List<GenreEntity>){
        viewState = viewState.copy(genreList = list)
    }

    private fun setLoading(loading: Boolean){
        viewState = viewState.copy(loading = loading)
    }

    fun loadMovieGenres() = execute {
        setLoading(true)
        when(val response = movieInteractor.getGenres()){
            is Success -> {
                setLoading(false)
                if(response.data.isEmpty()){
                    setState(GenresState.SuccessEmptyList)
                }else{
                    setList(response.data)
                }
            }
            is Error -> {
                setLoading(false)
                setState(LoadingErrorState)
            }
        }
    }
}