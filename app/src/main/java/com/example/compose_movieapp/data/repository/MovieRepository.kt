package com.example.compose_movieapp.data.repository

import com.example.compose_movieapp.data.BoxOffice
import com.example.compose_movieapp.data.BoxOfficeResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(targetDate: String) : Flow<BoxOfficeResponse>

    suspend fun getOneMovie(name: String) : Flow<BoxOffice>

    suspend fun saveMovieData(boxOffice: BoxOffice)
}