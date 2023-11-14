package com.example.sprint15independentwork.presentation.poster

import android.app.Activity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.sprint15independentwork.R

class PosterPresenter(private val view: PosterView, private val imageUrl: String) {

    fun onCreate() {
        view.setupPosterImage(imageUrl)
    }
}