package com.example.android.movies.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.movies.data.GenreRepository
import com.example.android.movies.data.OperationResult
import com.example.android.movies.models.Genre
import com.example.android.movies.models.interfaces.IMoviesGenresRetrieve
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GenreViewModel(private val respository: IMoviesGenresRetrieve): ViewModel() {
    private val _genreList = MutableLiveData<List<Genre>>().apply { value = emptyList() }
    val genreList: LiveData<List<Genre>> = _genreList

    private val _isViewLoading=MutableLiveData<Boolean>()
    val isViewLoading:LiveData<Boolean> = _isViewLoading

    private val _onMessageError=MutableLiveData<Any>()
    val onMessageError:LiveData<Any> = _onMessageError

    private val _isEmptyList=MutableLiveData<Boolean>()
    val isEmptyList:LiveData<Boolean> = _isEmptyList

    fun loadMovieGenres(){
        _isViewLoading.postValue(true)
        viewModelScope.launch {
            val result: OperationResult<Genre> = withContext(Dispatchers.IO){
                respository.retrieveMuseums()
            }
            _isViewLoading.postValue(false)
            when(result){
                is OperationResult.Success -> {
                    if(result.data.isNullOrEmpty()){
                        _isEmptyList.postValue(true)
                    }else{
                        _genreList.value = result.data
                    }
                }
                is OperationResult.Error -> {
                    Log.e("ErrorGenres", "OperationResult.error")
                    _onMessageError.postValue(result.exception)
                }
            }
        }
    }
}