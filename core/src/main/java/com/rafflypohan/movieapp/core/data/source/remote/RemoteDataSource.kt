package com.rafflypohan.movieapp.core.data.source.remote

import com.rafflypohan.movieapp.core.data.source.remote.network.ApiResponse
import com.rafflypohan.movieapp.core.data.source.remote.network.ApiService
import com.rafflypohan.movieapp.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getPopularMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getPopularMovie()
                val result = response.result
                if (result.isNotEmpty()) emit(ApiResponse.Success(response.result))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}