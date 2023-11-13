package com.example.sprint15independentwork.presentation.movies

interface MoviesView {
    fun showPlaceholderMessage(isVisible: Boolean)

    fun showMoviesList(isVisible: Boolean)

    fun showProgressBar(isVisible: Boolean)
    fun changePlaceholderText(newPlaceholderText: String)
}