package com.example.sprint15independentwork.presentation.movies

import com.example.sprint15independentwork.domain.models.Movie

interface MoviesView {

    fun render(state: MoviesState)

    fun showMessage(additionalMessage: String)
}