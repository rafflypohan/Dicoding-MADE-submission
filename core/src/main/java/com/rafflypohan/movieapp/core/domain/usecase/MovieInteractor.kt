package com.rafflypohan.movieapp.core.domain.usecase

import com.rafflypohan.movieapp.core.data.Resource
import com.rafflypohan.movieapp.core.domain.model.Movie
import com.rafflypohan.movieapp.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getPopularMovie(): Flow<Resource<List<Movie>>> = movieRepository.getPopularMovie()

    override fun getFavoriteMovie(): Flow<List<Movie>> = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)

}