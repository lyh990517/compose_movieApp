package com.example.compose_movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_movieapp.MovieState
import com.example.compose_movieapp.domain.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getMovieListUseCase: GetMovieListUseCase) :
    ViewModel() {
    private val _uiState = MutableStateFlow<MovieState>(MovieState.Loading)
    val uiState get() = _uiState
    fun getMovies(date: String) = viewModelScope.launch {
        getMovieListUseCase.getMovies(date).catch {
        }.collect {
            _uiState.value = MovieState.Success(it.boxofficeResult.dailyBoxOfficeList)
        }
    }
}