package com.example.sprint15independentwork.data.network

import com.example.sprint15independentwork.data.dto.MoviesSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApiService {
    @GET("/en/API/SearchMovie/k_ddp36btw/{expression}")
    fun findMovies(@Path("expression") expression: String): Call<MoviesSearchResponse>
}