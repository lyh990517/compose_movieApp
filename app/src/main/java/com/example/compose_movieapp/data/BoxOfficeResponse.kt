package com.example.compose_movieapp.data

import com.google.gson.annotations.SerializedName

data class BoxOfficeResponse(
    @SerializedName("boxOfficeResult")
    val boxofficeResult: BoxOfficeResult
)
