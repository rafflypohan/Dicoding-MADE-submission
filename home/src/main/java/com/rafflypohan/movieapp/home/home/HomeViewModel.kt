package com.rafflypohan.movieapp.home.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rafflypohan.movieapp.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val popularMovie = movieUseCase.getPopularMovie().asLiveData()
}