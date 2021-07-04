package com.rafflypohan.movieapp.detail

import androidx.lifecycle.ViewModel
import com.rafflypohan.movieapp.core.domain.model.Movie
import com.rafflypohan.movieapp.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie:Movie, state: Boolean) =
        movieUseCase.setFavoriteMovie(movie, state)
}