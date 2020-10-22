package com.example.android.movies.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.movies.bl.IMovieDetailRetrieve
import com.example.android.movies.data.OperationResult
import com.example.android.movies.models.MovieCast
import com.example.android.movies.models.MovieCredits
import com.example.android.movies.models.MovieDetail
import com.example.android.movies.models.ProductionCompany
import com.example.android.movies.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailViewModel(val repository: IMovieDetailRetrieve): ViewModel() {
    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail: LiveData<MovieDetail> = _movieDetail

    private val _movieProducers = MutableLiveData<List<ProductionCompany>>().apply { value = emptyList() }
    val movieProducers: LiveData<List<ProductionCompany>> = _movieProducers

    private val _movieCast = MutableLiveData<List<MovieCast>>().apply { value = emptyList() }
    val movieCast: MutableLiveData<List<MovieCast>> = _movieCast

    private val _isViewLoading=MutableLiveData<Boolean>()
    val isViewLoading:LiveData<Boolean> = _isViewLoading

    private val _onMessageError=MutableLiveData<Any>()
    val onMessageError:LiveData<Any> = _onMessageError

    private val _movieImage = MutableLiveData<String>()
    val movieImage: LiveData<String> = _movieImage

    private val _movieOverview = MutableLiveData<String>()
    val movieOverview: LiveData<String> = _movieOverview

    private val _movieRate = MutableLiveData<String>()
    val movieRate: LiveData<String> = _movieRate

    private val _movieReleaseDate = MutableLiveData<String>()
    val movieReleaseDate: LiveData<String> = _movieReleaseDate

    private val _urlHomepage = MutableLiveData<String>()
    val urlHomepage: LiveData<String> = _urlHomepage

    fun loadMovieDetail(movie_id: Int){
        _isViewLoading.postValue(true)
        viewModelScope.launch {
            val result: OperationResult<MovieDetail> = withContext(Dispatchers.IO){
                repository.retrieveMovieDetail(movie_id)
            }
            _isViewLoading.postValue(false)
            when(result){
                is OperationResult.Success -> {
                    if(result.data != null){
                        bind(result.data)
                    }else{
                        // La pelicula no tiene detalles
                        _onMessageError.postValue(null)
                    }
                }

                is OperationResult.Error -> {
                    Log.e("ErrorMovieDetail", "OperationResult.error")
                    _onMessageError.postValue(result.exception)
                }
            }
        }
    }

    private fun bind(movie: MovieDetail){
        _movieImage.postValue(Constants.IMAGES+movie.backdrop_path)
        _movieOverview.postValue(movie.overview)
        _movieRate.postValue(movie.vote_average.toString())
        _movieReleaseDate.postValue(movie.release_date)
        _movieProducers.postValue(movie.production_companies)
        _movieCast.postValue(movie.credits?.cast)
        _urlHomepage.postValue(movie.homepage)
    }
}