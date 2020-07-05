package com.example.android.movies.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieListByGenreResponse(val page: Int,
                                    val total_pages: Int,
                                    val total_results: Int,
                                    val results: List<MovieList>,
                                    val status_code: Int?,
                                    val status_message: String?): Parcelable