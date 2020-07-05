package com.example.android.movies.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductionCompany(val id: Int, val name: String, val logo_path: String): Parcelable