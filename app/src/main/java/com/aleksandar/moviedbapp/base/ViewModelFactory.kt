package com.aleksandar.moviedbapp.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aleksandar.moviedbapp.ui.movies.landing.MovieItemViewModel
import com.aleksandar.moviedbapp.ui.movies.landing.MoviesLandingViewModel

class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        @Suppress("UNCHECKED_CAST") when {
            modelClass.isAssignableFrom(MoviesLandingViewModel::class.java)  -> {
                return MoviesLandingViewModel() as T
            }
            modelClass.isAssignableFrom(MovieItemViewModel::class.java) -> {
                return MovieItemViewModel() as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}