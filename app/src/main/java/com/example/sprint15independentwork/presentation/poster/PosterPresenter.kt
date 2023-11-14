package com.example.sprint15independentwork.presentation.poster

class PosterPresenter(private val view: PosterView, private val imageUrl: String) {

    fun onCreate() {
        view.setupPosterImage(imageUrl)
    }
}