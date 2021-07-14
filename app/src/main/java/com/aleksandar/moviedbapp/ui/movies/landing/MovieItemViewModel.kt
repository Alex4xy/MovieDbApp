package com.aleksandar.moviedbapp.ui.movies.landing

import androidx.lifecycle.MutableLiveData
import com.aleksandar.moviedbapp.BuildConfig
import com.aleksandar.moviedbapp.base.BaseViewModel
import com.aleksandar.moviedbapp.model.MoviesResponse

class MovieItemViewModel: BaseViewModel() {
    private val movieTitle = MutableLiveData<String>()
    private val imageUrl = MutableLiveData<String>()

    fun bind(movie: MoviesResponse.Result){
        movieTitle.value = movie.title

        val posterPath = movie.posterPath
        imageUrl.value = BuildConfig.POSTER_W300_URL + posterPath
    }

    fun getMovieTitle():MutableLiveData<String>{
        return movieTitle
    }

    fun getImageUrl():MutableLiveData<String>{
        return imageUrl
    }
}