package com.aleksandar.moviedbapp.ui.movies.details

import androidx.lifecycle.MutableLiveData
import com.aleksandar.moviedbapp.BuildConfig
import com.aleksandar.moviedbapp.base.BaseViewModel
import com.aleksandar.moviedbapp.model.MoviesResponse

class SimilarShowItemViewModel: BaseViewModel() {
    private val movieTitle = MutableLiveData<String?>()
    private val imageUrl = MutableLiveData<String>()

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
    }

    fun getMovieTitle(): MutableLiveData<String?> {
        return movieTitle
    }

    fun getImageUrl():MutableLiveData<String>{
        return imageUrl
    }
}