package com.aleksandar.moviedbapp.base

import androidx.lifecycle.ViewModel
import com.aleksandar.moviedbapp.network.injection.DaggerViewModelInjector
import com.aleksandar.moviedbapp.network.injection.ViewModelInjector
import com.aleksandar.moviedbapp.network.module.NetworkModule
import com.aleksandar.moviedbapp.ui.movies.details.DetailsViewModel
import com.aleksandar.moviedbapp.ui.movies.details.SimilarShowItemViewModel
import com.aleksandar.moviedbapp.ui.movies.landing.MovieItemViewModel
import com.aleksandar.moviedbapp.ui.movies.landing.MoviesLandingViewModel


abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }
    
    private fun inject() {
        when (this) {
            is MoviesLandingViewModel -> injector.inject(this)
            is MovieItemViewModel -> injector.inject(this)
            is DetailsViewModel -> injector.inject(this)
            is SimilarShowItemViewModel -> injector.inject(this)
        }
    }
}