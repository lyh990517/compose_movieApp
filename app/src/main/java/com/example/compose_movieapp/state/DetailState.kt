package com.example.compose_movieapp.state

import com.example.compose_movieapp.data.BoxOffice

sealed class DetailState {

    object Loading : DetailState()

    data class Success(val data: BoxOffice) : DetailState()

    data class Error(val e: Throwable) : DetailState()
}
