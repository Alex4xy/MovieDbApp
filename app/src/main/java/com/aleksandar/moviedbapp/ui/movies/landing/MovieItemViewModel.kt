package com.aleksandar.moviedbapp.ui.movies.landing

import androidx.lifecycle.MutableLiveData
import com.aleksandar.moviedbapp.BuildConfig
import com.aleksandar.moviedbapp.base.BaseViewModel
import com.aleksandar.moviedbapp.model.MoviesResponse

class MovieItemViewModel: BaseViewModel() {
    private val movieTitle = MutableLiveData<String?>()
    private val imageUrl = MutableLiveData<String>()
    private val rating = MutableLiveData<String?>()

    fun bind(movie: MoviesResponse.Result){
        when {
            movie.originalTitle?.isNotEmpty() == true -> {
                movieTitle.value = movie.originalTitle
            }
            movie.title?.isNotEmpty() == true        -> {
                movieTitle.value = movie.title
            }
            movie.originalName?.isNotEmpty() == true -> {
                movieTitle.value = movie.originalName
            }
        }

        val posterPath = movie.posterPath
        imageUrl.value = BuildConfig.POSTER_W300_URL + posterPath

        rating.value = "Rating: " + movie.voteAverage.toString()
    }

    fun getMovieTitle(): MutableLiveData<String?> {
        return movieTitle
    }

    fun getImageUrl():MutableLiveData<String>{
        return imageUrl
    }

    fun getRating(): MutableLiveData<String?> {
        return rating
    }
}