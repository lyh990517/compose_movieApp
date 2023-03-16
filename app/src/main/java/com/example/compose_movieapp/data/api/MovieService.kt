package com.example.compose_movieapp.data.api

import com.example.compose_movieapp.data.BoxOfficeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    suspend fun getDailyBoxOfficeList(
        @Query("key") apiKey: String = "df5a8f9d935c2beadde64a98bea56b11",
        @Query("targetDt") targetDt: String,
        @Query("itemPerPage") itemPerPage: String
    ): Response<BoxOfficeResponse>
}