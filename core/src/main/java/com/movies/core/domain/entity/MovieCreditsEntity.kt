package com.movies.core.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieCreditsEntity(
    val cast: List<MovieCastEntity>
): Parcelable
