package com.example.compose_movieapp

import com.example.compose_movieapp.data.BoxOffice

sealed class MovieState {
    object Loading : MovieState()
    data class Success(val data: List<BoxOffice>?) : MovieState()
}

