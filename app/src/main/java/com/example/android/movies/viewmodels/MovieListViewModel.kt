package com.example.android.movies.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.movies.bl.IMovieRetrieve
import com.example.android.movies.data.OperationResult
import com.example.android.movies.data.repositories.MovieListRepository
import com.example.android.movies.models.MovieList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel(private val repository: IMovieRetrieve): ViewModel() {
    private val _movieList = MutableLiveData<List<MovieList>>().apply { value = emptyList() }
    val movieList: LiveData<List<MovieList>> = _movieList

    private val _isViewLoading=MutableLiveData<Boolean>()
    val isViewLoading:LiveData<Boolean> = _isViewLoading

    private val _onMessageError=MutableLiveData<Any>()
    val onMessageError:LiveData<Any> = _onMessageError

    private val _isEmptyList=MutableLiveData<Boolean>()
    val isEmptyList:LiveData<Boolean> = _isEmptyList

    fun loadMoviesByGenre(genreId: String){
        _isViewLoading.postValue(true)
        viewModelScope.launch {
            val result: OperationResult<MovieList> = withContext(Dispatchers.IO){
                repository.retrieveMovies(genreId)
            }
            _isViewLoading.postValue(false)
            when(result){
                is OperationResult.Success -> {
                    if(result.data.isNullOrEmpty()){
                        _isEmptyList.postValue(true)
                    }else{
                        _movieList.value = result.data
                    }
                }
                is OperationResult.Error -> {
                    Log.e("ErrorMovies", "OperationResult.error")
                    _onMessageError.postValue(result.exception)
                }
            }
        }
    }
}