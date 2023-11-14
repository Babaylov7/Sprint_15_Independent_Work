package com.example.sprint15independentwork

import android.app.Application
import com.example.sprint15independentwork.presentation.movies.MoviesSearchPresenter

class MoviesApplication : Application() {

    var moviesSearchPresenter : MoviesSearchPresenter? = null

}