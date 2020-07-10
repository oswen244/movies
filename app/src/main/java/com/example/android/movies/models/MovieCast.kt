package com.example.android.movies.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieCast(val name: String?, val profile_path: String?, val character: String?): Parcelable