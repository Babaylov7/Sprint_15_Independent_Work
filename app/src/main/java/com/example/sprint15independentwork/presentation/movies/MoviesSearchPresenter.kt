package com.example.sprint15independentwork.presentation.movies

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.example.sprint15independentwork.Creator
import com.example.sprint15independentwork.R
import com.example.sprint15independentwork.domain.api.MoviesInteractor
import com.example.sprint15independentwork.domain.models.Movie

class MoviesSearchPresenter(
    private val context: Context
) {
    private val moviesInteractor = Creator.provideMoviesInteractor(context)

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }

    private var view: MoviesView? = null
    private var state: MoviesState? = null
    private var latestSearchText: String? = null

    private val movies = ArrayList<Movie>()

    private val handler = Handler(Looper.getMainLooper())
    private var lastSearchText: String? = null

    private val searchRunnable = Runnable {
        val newSearchText = lastSearchText ?: ""
        searchRequest(newSearchText)
    }

    fun searchDebounce(changedText: String) {
        if (latestSearchText == changedText) {
            return
        }

        this.latestSearchText = changedText
        this.lastSearchText = changedText
        handler.removeCallbacks(searchRunnable)
        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    fun onDestroy() {
        handler.removeCallbacks(searchRunnable)
    }
    fun attachView(view: MoviesView) {
        this.view = view
        state?.let { view.render(it) }
    }

    fun detachView() {
        this.view = null
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            renderState(
                MoviesState(
                    movies = movies,
                    isLoading = true,
                    errorMessage = null,
                )
            )

            moviesInteractor.searchMovies(newSearchText, object : MoviesInteractor.MoviesConsumer {
                override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                    handler.post {
                        if (foundMovies != null) {
                            movies.clear()
                            movies.addAll(foundMovies)
                        }

                        when {
                            errorMessage != null -> {
                                renderState(
                                    MoviesState(
                                        movies = emptyList(),
                                        isLoading = false,
                                        errorMessage = context.getString(R.string.something_went_wrong),
                                    )
                                )
                                view?.showMessage(errorMessage)
                            }

                            movies.isEmpty() -> {
                                renderState(
                                    MoviesState(
                                        movies = emptyList(),
                                        isLoading = false,
                                        errorMessage = context.getString(R.string.nothing_found),
                                    )
                                )
                            }

                            else -> {
                                renderState(
                                    MoviesState(
                                        movies = movies,
                                        isLoading = false,
                                        errorMessage = null,
                                    )
                                )
                            }
                        }

                    }
                }
            })
        }
    }

    private fun renderState(state: MoviesState) {
        this.state = state
        this.view?.render(state)
    }
}