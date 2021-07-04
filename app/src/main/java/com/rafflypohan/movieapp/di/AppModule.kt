package com.rafflypohan.movieapp.di

import com.rafflypohan.movieapp.core.domain.usecase.MovieInteractor
import com.rafflypohan.movieapp.core.domain.usecase.MovieUseCase
import com.rafflypohan.movieapp.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }

}

val viewModelModule = module {
    viewModel { DetailViewModel(get()) }
}

