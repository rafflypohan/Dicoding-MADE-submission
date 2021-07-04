package com.rafflypohan.movieapp.core.data.source.remote.network

import com.rafflypohan.movieapp.core.BuildConfig

object ApiKey {
    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
}