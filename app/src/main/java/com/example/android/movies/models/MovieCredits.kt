package com.example.android.movies.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieCredits(val cast: List<MovieCast>): Parcelable