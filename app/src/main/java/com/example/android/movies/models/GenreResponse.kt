package com.example.android.movies.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreResponse (
    val genres: List<Genre>?,
    val status_code: Int?,
    val status_message: String?
):Parcelable