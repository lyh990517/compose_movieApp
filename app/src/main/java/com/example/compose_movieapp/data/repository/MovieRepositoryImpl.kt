package com.example.compose_movieapp.data.repository

import com.example.compose_movieapp.data.BoxOffice
import com.example.compose_movieapp.data.BoxOfficeResponse
import com.example.compose_movieapp.data.api.MovieService
import com.example.compose_movieapp.data.database.MovieDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieService,
    private val dao: MovieDao
) : MovieRepository {
    override suspend fun getMovies(targetDate: String): Flow<BoxOfficeResponse> =
        flow {
            val response = api.getDailyBoxOfficeList(targetDt = targetDate, itemPerPage = "20")
            response.body()?.let { response ->
                emit(response)
            }
        }

    override suspend fun getOneMovie(name: String): Flow<BoxOffice> = flow {
        emit(dao.getOne(name))
    }


    override suspend fun saveMovieData(boxOffice: BoxOffice) {
        dao.insertOne(boxOffice)
    }

}