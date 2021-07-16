package com.aleksandar.moviedbapp.ui.movies.details

import androidx.lifecycle.MutableLiveData
import com.aleksandar.moviedbapp.BuildConfig
import com.aleksandar.moviedbapp.base.BaseViewModel
import com.aleksandar.moviedbapp.model.MovieDetailsResponse

class SeasonItemViewModel: BaseViewModel() {
    private val movieTitle = MutableLiveData<String?>()
    private val imageUrl = MutableLiveData<String>()

    fun bind(season: MovieDetailsResponse.Season){
        movieTitle.value = season.name

        val posterPath = season.posterPath
        imageUrl.value = BuildConfig.POSTER_W500_URL + posterPath
    }

    fun getMovieTitle(): MutableLiveData<String?> {
        return movieTitle
    }

    fun getImageUrl():MutableLiveData<String>{
        return imageUrl
    }
}