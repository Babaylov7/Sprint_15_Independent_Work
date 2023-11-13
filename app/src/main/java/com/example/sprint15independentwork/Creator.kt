package com.example.sprint15independentwork

import android.app.Activity
import android.content.Context
import com.example.sprint15independentwork.data.MoviesRepositoryImpl
import com.example.sprint15independentwork.data.network.RetrofitNetworkClient
import com.example.sprint15independentwork.domain.api.MoviesInteractor
import com.example.sprint15independentwork.domain.api.MoviesRepository
import com.example.sprint15independentwork.domain.impl.MoviesInteractorImpl
import com.example.sprint15independentwork.presentation.movies.MoviesSearchPresenter
import com.example.sprint15independentwork.presentation.PosterController
import com.example.sprint15independentwork.presentation.movies.MoviesView
import com.example.sprint15independentwork.ui.movies.MoviesAdapter

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchPresenter(moviesView: MoviesView, context: Context): MoviesSearchPresenter {
        return MoviesSearchPresenter(moviesView, context)
    }

    fun providePosterController(activity: Activity): PosterController {
        return PosterController(activity)
    }
}