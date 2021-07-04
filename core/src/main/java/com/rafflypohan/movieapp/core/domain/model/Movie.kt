package com.rafflypohan.movieapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String?,
    val voteAverage: Double,
    var isFavorite: Boolean
) : Parcelable