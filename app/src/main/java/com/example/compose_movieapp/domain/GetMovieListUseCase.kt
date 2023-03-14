package com.example.compose_movieapp.domain

import com.example.compose_movieapp.data.BoxOfficeResponse
import com.example.compose_movieapp.data.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend fun getMovies(date: String) =
        flow {
            repository.getMovies(targetDate = date)
                .collect {
                    emit(it)
                }
        }
}