package com.example.sprint15independentwork.presentation.movies

import com.example.sprint15independentwork.domain.models.Movie

interface MoviesView {
    fun showPlaceholderMessage(isVisible: Boolean)
    fun showMoviesList(isVisible: Boolean)
    fun showProgressBar(isVisible: Boolean)
    fun changePlaceholderText(newPlaceholderText: String)
    fun updateMoviesList(newMoviesList: List<Movie>)
}