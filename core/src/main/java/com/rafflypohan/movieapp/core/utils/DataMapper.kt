package com.rafflypohan.movieapp.core.utils

import android.util.Log
import com.rafflypohan.movieapp.core.data.source.local.entities.MovieEntity
import com.rafflypohan.movieapp.core.data.source.remote.response.MovieResponse
import com.rafflypohan.movieapp.core.domain.model.Movie

object DataMapper {
    fun mapResponseToEntities(input: List<MovieResponse>) : List<MovieEntity>{
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate ?: "-",
                voteAverage = it.voteAverage,
                isFavorite = false
            )
            it.releaseDate?.let { it1 -> Log.d("aa", it1) }
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>) : List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) =
        MovieEntity(
            id = input.id,
            title = input.title,
            overview = input.overview,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage,
            isFavorite = input.isFavorite
        )
}