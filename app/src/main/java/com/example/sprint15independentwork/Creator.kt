package com.example.sprint15independentwork

import android.app.Activity
import com.example.sprint15independentwork.data.MoviesRepositoryImpl
import com.example.sprint15independentwork.data.network.RetrofitNetworkClient
import com.example.sprint15independentwork.domain.api.MoviesInteractor
import com.example.sprint15independentwork.domain.api.MoviesRepository
import com.example.sprint15independentwork.domain.impl.MoviesInteractorImpl
import com.example.sprint15independentwork.presentation.MoviesSearchController
import com.example.sprint15independentwork.presentation.PosterController
import com.example.sprint15independentwork.ui.movies.MoviesAdapter

object Creator {
    private fun getMoviesRepository(): MoviesRepository{
        return MoviesRepositoryImpl(RetrofitNetworkClient())
    }

    fun provideMoviesInteractor(): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository())
    }

    fun provideMoviesSearchController(activity: Activity, adapter: MoviesAdapter): MoviesSearchController {
        return MoviesSearchController(activity, adapter)
    }

    fun providePosterController(activity: Activity): PosterController {
        return PosterController(activity)
    }
}