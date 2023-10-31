package com.example.sprint15independentwork.data.network

import com.example.sprint15independentwork.data.NetworkClient
import com.example.sprint15independentwork.data.dto.MoviesSearchRequest
import com.example.sprint15independentwork.data.dto.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitNetworkClient: NetworkClient {

    private val imbBaseUrl = "https://imdb-api.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(imbBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val imbService = retrofit.create(IMDbApiService::class.java)

    override fun doRequest(dto: Any): Response {
        if (dto is MoviesSearchRequest) {
            val resp = imbService.findMovies(dto.expression).execute()
            val body = resp.body() ?: Response()
            return body.apply { resultCode = resp.code() }

        } else {
            return Response().apply { resultCode = 400 }
        }
    }

}