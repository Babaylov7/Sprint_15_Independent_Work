package com.example.sprint15independentwork.domain.api

import com.example.sprint15independentwork.Resource
import com.example.sprint15independentwork.domain.models.Movie

interface MoviesRepository {
    fun searchMovies(expression: String): Resource<List<Movie>>
}