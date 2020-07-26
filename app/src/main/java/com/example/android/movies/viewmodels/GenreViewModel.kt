package com.example.android.movies.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.movies.data.OperationResultList
import com.example.android.movies.models.Genre
import com.example.android.movies.bl.IMoviesGenresRetrieve
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GenreViewModel(private val repository: IMoviesGenresRetrieve): ViewModel() {
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
            val resultList: OperationResultList<Genre> = withContext(Dispatchers.IO){
                repository.retrieveGenres()
            }
            _isViewLoading.postValue(false)
            when(resultList){
                is OperationResultList.Success -> {
                    if(resultList.data.isNullOrEmpty()){
                        _isEmptyList.postValue(true)
                    }else{
                        _genreList.value = resultList.data
                    }
                }
                is OperationResultList.Error -> {
                    Log.e("ErrorGenres", "OperationResult.error")
                    _onMessageError.postValue(resultList.exception)
                }
            }
        }
    }
}