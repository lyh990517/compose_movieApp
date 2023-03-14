package com.example.compose_movieapp.data

import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(targetDate: String) : Flow<BoxOfficeResponse>
}