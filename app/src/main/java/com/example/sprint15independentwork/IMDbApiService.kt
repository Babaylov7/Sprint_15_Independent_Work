package com.example.sprint15independentwork

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApiService {
    @GET("/en/API/SearchMovie/YOUR_API_KEY/{expression}")
    fun findMovies(@Path("expression") expression: String): Call<MoviesSearchResponse>
}