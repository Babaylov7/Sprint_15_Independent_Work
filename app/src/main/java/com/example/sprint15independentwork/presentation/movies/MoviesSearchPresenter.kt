package com.example.sprint15independentwork.presentation.movies

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sprint15independentwork.Creator
import com.example.sprint15independentwork.R
import com.example.sprint15independentwork.domain.api.MoviesInteractor
import com.example.sprint15independentwork.domain.models.Movie
import com.example.sprint15independentwork.ui.movies.MoviesAdapter

class MoviesSearchPresenter(
    private val view: MoviesView,
    private val context: Context,
    private val adapter: MoviesAdapter
) {
    private val moviesInteractor = Creator.provideMoviesInteractor(context)

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }

    private val movies = ArrayList<Movie>()

    private val handler = Handler(Looper.getMainLooper())
    private var lastSearchText: String? = null

    private val searchRunnable = Runnable {
        val newSearchText = lastSearchText ?: ""
        searchRequest(newSearchText)
    }


    fun searchDebounce(changedText: String) {
        this.lastSearchText = changedText
        handler.removeCallbacks(searchRunnable)
        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    fun onCreate() {
        adapter.movies = movies
    }

    fun onDestroy() {
        handler.removeCallbacks(searchRunnable)
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            view.showPlaceholderMessage(false)
            view.showMoviesList(false)
            view.showProgressBar(true)

            moviesInteractor.searchMovies(
                newSearchText,
                object : MoviesInteractor.MoviesConsumer {
                    override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                        handler.post {
                            view.showProgressBar(false)
                            if (foundMovies != null) {
                                movies.clear()
                                movies.addAll(foundMovies)
                                adapter.notifyDataSetChanged()
                                view.showMoviesList(true)
                            }
                            if (errorMessage != null) {
                                showMessage("Что-то пошло не так", errorMessage)
                            } else if (movies.isEmpty()) {
                                showMessage("Ничего не нашлось", "")
                            } else {
                                hideMessage()
                            }
                        }
                    }
                }
            )
        }
    }

    private fun showMessage(text: String, additionalMessage: String) {
        if (text.isNotEmpty()) {
            view.showPlaceholderMessage(true)
            movies.clear()
            adapter.notifyDataSetChanged()
            view.changePlaceholderText(text)
            if (additionalMessage.isNotEmpty()) {
                // Поменяли view на Context
                Toast.makeText(context, additionalMessage, Toast.LENGTH_LONG)
                    .show()
            }
        } else {
            view.showPlaceholderMessage(false)
        }
    }

    private fun hideMessage() {
        // Заменили работу с элементами UI на
        // вызовы методов интерфейса
        view.showPlaceholderMessage(false)
    }
}