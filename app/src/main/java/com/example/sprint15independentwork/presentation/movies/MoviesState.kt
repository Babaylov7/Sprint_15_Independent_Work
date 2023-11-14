package com.example.sprint15independentwork.presentation.movies

import com.example.sprint15independentwork.domain.models.Movie

data class MoviesState(
    val movies: List<Movie>,
    val isLoading: Boolean,
    val errorMessage: String?
)
