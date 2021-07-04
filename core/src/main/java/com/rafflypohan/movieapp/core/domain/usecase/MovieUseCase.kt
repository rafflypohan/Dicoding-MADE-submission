package com.rafflypohan.movieapp.core.domain.usecase

import com.rafflypohan.movieapp.core.data.Resource
import com.rafflypohan.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getPopularMovie() : Flow<Resource<List<Movie>>>
    fun getFavoriteMovie() : Flow<List<Movie>>
//    fun getSearchMovie()
//    fun setSearchMovie()
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}