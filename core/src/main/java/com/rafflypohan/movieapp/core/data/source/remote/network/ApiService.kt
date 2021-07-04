package com.rafflypohan.movieapp.core.data.source.remote.network

import com.rafflypohan.movieapp.core.data.source.remote.network.ApiKey.API_KEY
import com.rafflypohan.movieapp.core.data.source.remote.response.ResponseItemMovieList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("api_key") apiKey: String = API_KEY) : ResponseItemMovieList
}