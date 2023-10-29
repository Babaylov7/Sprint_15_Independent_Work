package com.example.sprint15independentwork

data class MoviesSearchResponse(val searchType: String,
                                val expression: String,
                                val results: List<Movie>)