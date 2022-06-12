package com.movies.core.domain.entity

import android.os.Parcelable
import com.movies.core.data.model.MovieDto
import com.movies.core.domain.entity.ProductionCompanyEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    val id: Int?,
    val backdrop_path: String?,
    val poster_path: String?,
    val overview: String?,
    val release_date: String?,
    val title: String?,
    val vote_average: Float?,
    val homepage: String?,
    val production_companies: List<ProductionCompanyEntity>?,
    val credits: MovieCreditsEntity?
): Parcelable

fun MovieDto.mapToDomain() = MovieEntity(
    id,
    backdrop_path,
    poster_path,
    overview,
    release_date,
    title,
    vote_average,
    homepage,
    production_companies,
    credits
)
