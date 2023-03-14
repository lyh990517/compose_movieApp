package com.example.compose_movieapp.data

import android.util.Log
import com.example.compose_movieapp.api.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MovieService) : MovieRepository {
    override suspend fun getMovies(targetDate: String): Flow<BoxOfficeResponse> =
        flow {
            val response = api.getDailyBoxOfficeList(targetDt = targetDate)
            response.body()?.let { response ->
                emit(response)
            }
        }

}