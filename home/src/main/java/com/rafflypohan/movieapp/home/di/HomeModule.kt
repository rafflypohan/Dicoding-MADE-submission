package com.rafflypohan.movieapp.home.di

import com.rafflypohan.movieapp.home.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get()) }
}