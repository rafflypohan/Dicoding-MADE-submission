package com.rafflypohan.movieapp.core.data.source.local

import com.rafflypohan.movieapp.core.data.source.local.entities.MovieEntity
import com.rafflypohan.movieapp.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {
    fun getPopularMovie() : Flow<List<MovieEntity>> = movieDao.getPopularMovie()

    fun getFavoriteMovie() : Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean){
        movie.isFavorite = newState
        movieDao.updateFavorite(movie)
    }
}