package com.movies.core.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieCastEntity(
    val name: String?,
    val profile_path: String?,
    val character: String?
): Parcelable
