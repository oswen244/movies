package com.movies.core.data.model

import android.os.Parcelable
import com.movies.core.domain.entity.GenreEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreDto(
    val genres: List<GenreEntity>,
    val status_code: Int?,
    val status_message: String?
): Parcelable