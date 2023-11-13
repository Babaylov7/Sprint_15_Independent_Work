package com.example.sprint15independentwork.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.sprint15independentwork.data.NetworkClient
import com.example.sprint15independentwork.data.dto.MoviesSearchRequest
import com.example.sprint15independentwork.data.dto.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitNetworkClient(private val context: Context) : NetworkClient {

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

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }

}