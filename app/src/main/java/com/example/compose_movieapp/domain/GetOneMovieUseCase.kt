package com.example.compose_movieapp.domain

import com.example.compose_movieapp.data.BoxOffice
import com.example.compose_movieapp.data.database.MovieDao
import com.example.compose_movieapp.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOneMovieUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend fun getOne(name: String): Flow<BoxOffice> = repository.getOneMovie(name)
}