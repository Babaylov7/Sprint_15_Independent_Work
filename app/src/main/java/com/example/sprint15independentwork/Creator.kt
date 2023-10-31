package com.example.sprint15independentwork

import com.example.sprint15independentwork.data.MoviesRepositoryImpl
import com.example.sprint15independentwork.data.network.RetrofitNetworkClient
import com.example.sprint15independentwork.domain.api.MoviesInteractor
import com.example.sprint15independentwork.domain.api.MoviesRepository
import com.example.sprint15independentwork.domain.impl.MoviesInteractorImpl

object Creator {
    private fun getMoviesRepository(): MoviesRepository{
        return MoviesRepositoryImpl(RetrofitNetworkClient())
    }

    fun provideMoviesInteractor(): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository())
    }
}