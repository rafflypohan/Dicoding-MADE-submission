package com.rafflypohan.movieapp.core.data

import com.rafflypohan.movieapp.core.data.source.local.LocalDataSource
import com.rafflypohan.movieapp.core.data.source.remote.RemoteDataSource
import com.rafflypohan.movieapp.core.data.source.remote.network.ApiResponse
import com.rafflypohan.movieapp.core.data.source.remote.response.MovieResponse
import com.rafflypohan.movieapp.core.domain.model.Movie
import com.rafflypohan.movieapp.core.domain.repository.IMovieRepository
import com.rafflypohan.movieapp.core.utils.AppExecutors
import com.rafflypohan.movieapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getPopularMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> = localDataSource.getPopularMovie().map { DataMapper.mapEntitiesToDomain(it) }

            override fun shouldFetch(data: List<Movie>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> = remoteDataSource.getPopularMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> = localDataSource.getFavoriteMovie().map { DataMapper.mapEntitiesToDomain(it) }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute{ localDataSource.setFavoriteMovie(movieEntity, state) }
    }
}