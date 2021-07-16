package com.aleksandar.moviedbapp.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.aleksandar.moviedbapp.model.database.AppDatabase
import com.aleksandar.moviedbapp.ui.movies.details.DetailsViewModel
import com.aleksandar.moviedbapp.ui.movies.details.SimilarShowItemViewModel
import com.aleksandar.moviedbapp.ui.movies.landing.MovieItemViewModel
import com.aleksandar.moviedbapp.ui.movies.landing.MoviesLandingViewModel

class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        @Suppress("UNCHECKED_CAST") when {
            modelClass.isAssignableFrom(MoviesLandingViewModel::class.java)         -> {
                val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "posts").build()
                return MoviesLandingViewModel(db.moviesDao()) as T
            }
            modelClass.isAssignableFrom(MovieItemViewModel::class.java)             -> {
                return MovieItemViewModel() as T
            }modelClass.isAssignableFrom(DetailsViewModel::class.java)              -> {
                return DetailsViewModel() as T
            }modelClass.isAssignableFrom(SimilarShowItemViewModel::class.java) -> {
                return SimilarShowItemViewModel() as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}