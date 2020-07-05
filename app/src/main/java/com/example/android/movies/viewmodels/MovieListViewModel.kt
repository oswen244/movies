package com.example.android.movies.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.movies.bl.IMovieRetrieve
import com.example.android.movies.data.OperationResult
import com.example.android.movies.data.OperationResultList
import com.example.android.movies.models.MovieList
import com.example.android.movies.models.MovieListByGenreResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel(private val repository: IMovieRetrieve): ViewModel() {
    private val _movieList = MutableLiveData<MutableList<MovieList>>().apply { value = arrayListOf() }
    val movieList: LiveData<MutableList<MovieList>> = _movieList

    private val _isViewLoading=MutableLiveData<Boolean>()
    val isViewLoading:LiveData<Boolean> = _isViewLoading

    private val _onMessageError=MutableLiveData<Any>()
    val onMessageError:LiveData<Any> = _onMessageError

    private val _isEmptyList=MutableLiveData<Boolean>()
    val isEmptyList:LiveData<Boolean> = _isEmptyList

    private val _maxPages = MutableLiveData<Int>()
    val maxPages: LiveData<Int> = _maxPages

    fun loadMoviesByGenre(genreId: String, page: Int){
        _isViewLoading.postValue(true)
        viewModelScope.launch {
            val resultList: OperationResult<MovieListByGenreResponse> = withContext(Dispatchers.IO){
                repository.retrieveMovies(genreId, page)
            }
            _isViewLoading.postValue(false)
            when(resultList){
                is OperationResult.Success -> {
                    if(resultList.data?.results.isNullOrEmpty()){
                        _isEmptyList.postValue(true)
                    }else{
                        _maxPages.postValue(resultList.data?.total_pages)
                        _movieList.value = resultList.data?.results as MutableList<MovieList>
                    }
                }
                is OperationResult.Error -> {
                    Log.e("ErrorMovies", "OperationResult.error")
                    _onMessageError.postValue(resultList.exception)
                }
            }
        }
    }
}