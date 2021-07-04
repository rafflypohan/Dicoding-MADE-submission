package com.rafflypohan.movieapp.core.domain.repository

import com.rafflypohan.movieapp.core.data.Resource
import com.rafflypohan.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getPopularMovie() : Flow<Resource<List<Movie>>>

    fun getFavoriteMovie() : Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}