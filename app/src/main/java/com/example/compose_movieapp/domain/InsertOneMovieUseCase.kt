package com.example.compose_movieapp.domain

import com.example.compose_movieapp.data.BoxOffice
import com.example.compose_movieapp.data.database.MovieDao
import com.example.compose_movieapp.data.repository.MovieRepository
import javax.inject.Inject

class InsertOneMovieUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend fun insertOne(boxOffice: BoxOffice) {
        repository.saveMovieData(boxOffice)
    }
}