package com.example.compose_movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_movieapp.state.MovieState
import com.example.compose_movieapp.domain.GetMovieListUseCase
import com.example.compose_movieapp.domain.GetOneMovieUseCase
import com.example.compose_movieapp.domain.InsertOneMovieUseCase
import com.example.compose_movieapp.state.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val getOneMovieUseCase: GetOneMovieUseCase,
    private val insertOneMovieUseCase: InsertOneMovieUseCase
) :
    ViewModel() {
    private val _uiState = MutableStateFlow<MovieState>(MovieState.Loading)
    val uiState get() = _uiState

    private val _detailState = MutableStateFlow<DetailState>(DetailState.Loading)
    val detailState get() = _detailState

    private var _fetchJob: Job = Job()
    fun getMovies(date: String) {
        _fetchJob = viewModelScope.launch {
            if (_fetchJob.isActive) {
                Log.e(date, "cancel")
                _fetchJob.cancelAndJoin()
            }
            getMovieListUseCase.getMovies(date).catch {
                _uiState.value = MovieState.Error(it)
            }.collect {
                Log.e(date,"${it.boxofficeResult.dailyBoxOfficeList}")
                val state = MovieState.Success(it.boxofficeResult.dailyBoxOfficeList)
                _uiState.value = state
                state.data?.forEach { boxOffice ->
                    insertOneMovieUseCase.insertOne(boxOffice)
                }
            }
        }
    }

    fun getOneMovie(name: String) = viewModelScope.launch {
        getOneMovieUseCase.getOne(name).catch {
            _detailState.value = DetailState.Error(it)
        }.collect {
            _detailState.value = DetailState.Success(it)
        }
    }
}