package com.movies.core.domain.entity

import android.os.Parcelable
import com.movies.core.data.model.MoviesDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieListEntity (
    val page: Int,
    val total_pages: Int,
    val total_results: Int,
    val results: List<MovieEntity>,
    val status_code: Int?,
    val status_message: String?
): Parcelable

fun MoviesDto.mapToDomain() = MovieListEntity(
    page,
    total_pages,
    total_results,
    results,
    status_code,
    status_message
)