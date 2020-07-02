package com.example.android.movies.models.interfaces

import com.example.android.movies.data.OperationResult
import com.example.android.movies.models.Genre

interface IMoviesGenresRetrieve {
    suspend fun retrieveMuseums(): OperationResult<Genre>
}