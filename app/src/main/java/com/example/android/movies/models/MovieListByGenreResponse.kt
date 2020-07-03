package com.example.android.movies.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieListByGenreResponse(val results: List<MovieList>, val status_code: Int?, val status_message: String?): Parcelable