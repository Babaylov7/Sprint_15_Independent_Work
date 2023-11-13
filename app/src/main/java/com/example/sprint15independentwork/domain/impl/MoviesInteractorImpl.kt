package com.example.sprint15independentwork.domain.impl


import com.example.sprint15independentwork.Resource
import com.example.sprint15independentwork.domain.api.MoviesInteractor
import com.example.sprint15independentwork.domain.api.MoviesRepository
import java.util.concurrent.Executors

class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {
    private val executor = Executors.newCachedThreadPool()

    override fun searchMovies(expression: String, consumer: MoviesInteractor.MoviesConsumer) {
        executor.execute {
            when(val resource = repository.searchMovies(expression)) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(null, resource.message) }
            }
        }
    }
}