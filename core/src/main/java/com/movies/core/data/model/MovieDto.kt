package com.movies.core.data.model

import android.os.Parcelable
import com.movies.core.domain.entity.MovieCreditsEntity
import com.movies.core.domain.entity.ProductionCompanyEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDto(
    val id: Int,
    val backdrop_path: String?,
    val poster_path: String?,
    val overview: String?,
    val release_date: String?,
    val title: String?,
    val vote_average: Float?,
    val homepage: String?,
    val production_companies: List<ProductionCompanyEntity>?,
    val credits: MovieCreditsEntity?,
    val status_code: Int?,
    val status_message: String?
): Parcelable
