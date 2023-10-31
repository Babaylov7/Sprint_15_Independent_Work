package com.example.sprint15independentwork.data.dto

import com.example.sprint15independentwork.domain.models.Movie

data class MoviesSearchResponse(val searchType: String,
                                val expression: String,
                                val results: List<MovieDto>) : Response()