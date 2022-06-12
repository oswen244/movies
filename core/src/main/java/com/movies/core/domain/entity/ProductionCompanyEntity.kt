package com.movies.core.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductionCompanyEntity(val id: Int, val name: String, val logo_path: String): Parcelable
