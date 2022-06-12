package com.movies.core.data.model

import android.os.Parcelable
import com.movies.core.domain.entity.MovieEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesDto(
    val page: Int,
    val total_pages: Int,
    val total_results: Int,
    val results: List<MovieEntity>,
    val status_code: Int?,
    val status_message: String?
): Parcelable