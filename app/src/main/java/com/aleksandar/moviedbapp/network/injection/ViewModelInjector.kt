package com.aleksandar.moviedbapp.network.injection

import com.aleksandar.moviedbapp.network.module.NetworkModule
import com.aleksandar.moviedbapp.ui.movies.landing.MovieItemViewModel
import com.aleksandar.moviedbapp.ui.movies.landing.MoviesLandingViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton @Component(modules = [(NetworkModule::class)]) interface ViewModelInjector {

    fun inject(moviesLandingViewModel: MoviesLandingViewModel)
    fun inject(movieItemViewModel: MovieItemViewModel)

    @Component.Builder interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}