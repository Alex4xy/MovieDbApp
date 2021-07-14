package com.aleksandar.moviedbapp.ui.movies.landing

import com.aleksandar.moviedbapp.base.BaseViewModel
import com.aleksandar.moviedbapp.network.API
import com.aleksandar.moviedbapp.util.MEDIA_TYPE_MOVIE
import com.aleksandar.moviedbapp.util.TIME_WINDOW_WEEK
import com.aleksandar.moviedbapp.util.TMDB_API_KEY
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesLandingViewModel : BaseViewModel() {

    @Inject
    lateinit var api: API
    private lateinit var subscription: Disposable

    fun getMovies() {
        subscription = api.getMovies(MEDIA_TYPE_MOVIE, TIME_WINDOW_WEEK, TMDB_API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {}
            .doOnTerminate {}
            .subscribe(
                { result ->
                    //success
                },
                {
                    //error
                })
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}