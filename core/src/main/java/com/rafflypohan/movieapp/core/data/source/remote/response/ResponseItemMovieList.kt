package com.rafflypohan.movieapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseItemMovieList(
    @field:SerializedName("results")
    val result: List<MovieResponse>
)
