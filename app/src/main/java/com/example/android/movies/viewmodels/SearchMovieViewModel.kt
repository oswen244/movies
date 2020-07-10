package com.example.android.movies.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.movies.bl.IMovieRetrieve
import com.example.android.movies.bl.IMovieSearchRetrieve
import com.example.android.movies.data.OperationResult
import com.example.android.movies.models.MovieList
import com.example.android.movies.models.MovieListByGenreResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchMovieViewModel(private val repository: IMovieSearchRetrieve): ViewModel() {
    private val _moviesResult = MutableLiveData<MutableList<MovieList>>().apply { value = arrayListOf() }
    val movieResult: MutableLiveData<MutableList<MovieList>> = _moviesResult

    private val _isViewLoading=MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _maxPages = MutableLiveData<Int>()
    val maxPages: LiveData<Int> = _maxPages

    private val _currentPage = MutableLiveData<Int>()
    val currentPage: LiveData<Int> = _currentPage

    private val _isEmptyList=MutableLiveData<Boolean>()
    val isEmptyList:LiveData<Boolean> = _isEmptyList

    private val _onMessageError=MutableLiveData<Any>()
    val onMessageError:LiveData<Any> = _onMessageError

    fun searchMovie(query: String?, page: Int){
        _isViewLoading.postValue(true)
        viewModelScope.launch {
            val resultList: OperationResult<MovieListByGenreResponse> = withContext(Dispatchers.IO){
                repository.retrieveMovieSearch(query, page)
            }
            _isViewLoading.postValue(false)
            when(resultList){
                is OperationResult.Success -> {
                    if(resultList.data?.results.isNullOrEmpty()){
                        _isEmptyList.postValue(true)
                    }else{
                        _currentPage.value = resultList.data?.page
                        _maxPages.postValue(resultList.data?.total_pages)
                        _moviesResult.value = resultList.data?.results as MutableList<MovieList>
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