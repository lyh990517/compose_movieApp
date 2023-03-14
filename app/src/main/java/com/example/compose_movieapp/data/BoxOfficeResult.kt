package com.example.compose_movieapp.data

import com.google.gson.annotations.SerializedName

data class BoxOfficeResult(
    @SerializedName("boxofficeType") val boxOfficeType: String,
    @SerializedName("showRange") val showRange: String,
    @SerializedName("dailyBoxOfficeList") val dailyBoxOfficeList: List<BoxOffice>
)
