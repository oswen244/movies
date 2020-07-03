package com.example.android.movies.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieList(val id: Int, val backdrop_path: String, val title: String, val vote_average: Float): Parcelable
