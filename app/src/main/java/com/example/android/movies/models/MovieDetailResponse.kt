package com.example.android.movies.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetailResponse(val id: Int,
                               val backdrop_path: String?,
                               val poster_path: String?,
                               val overview: String?,
                               val release_date: String?,
                               val title: String?,
                               val vote_average: Float?,
                               val homepage: String?,
                               val production_companies: List<ProductionCompany>?,
                               val credits: MovieCredits?,
                               val status_code: Int?,
                               val status_message: String?): Parcelable
