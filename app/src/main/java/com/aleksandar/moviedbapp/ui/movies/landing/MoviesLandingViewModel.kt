package com.aleksandar.moviedbapp.ui.movies.landing

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.aleksandar.moviedbapp.R
import com.aleksandar.moviedbapp.base.BaseViewModel
import com.aleksandar.moviedbapp.model.MoviesResponse
import com.aleksandar.moviedbapp.network.API
import com.aleksandar.moviedbapp.util.MEDIA_TYPE_TV
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
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { getMovies() }
    val moviesAdapter: MoviesAdapter = MoviesAdapter()
    var currentPage = 1
    var totalAvailablePages = 1
    var isSearching: Boolean = false

    fun getMovies() {
        subscription = api.getMovies(MEDIA_TYPE_TV, TIME_WINDOW_WEEK, TMDB_API_KEY, currentPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                onGetMoviesStart()
            }
            .doOnTerminate {
                onGetMoviesFinish()
            }
            .subscribe(
                {
                    totalAvailablePages = it.totalPages
                    onGetMoviesSuccess(it.results)
                },
                {
                    onGetMoviesError()
                }
                      )
    }

    fun searchMovies(query:String) {
        subscription = api.searchMovies(TMDB_API_KEY,query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                onGetMoviesStart()
            }
            .doOnTerminate {
                onGetMoviesFinish()
            }
            .subscribe(
                {
                    onSearchMoviesSuccess(it.results)
                },
                {
                    onSearchMoviesError()
                }
                      )
    }

    private fun onGetMoviesStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onGetMoviesFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onGetMoviesSuccess(moviesList:ArrayList<MoviesResponse.Result>){
        moviesAdapter.updateMoviesList(moviesList, false)
    }

    private fun onGetMoviesError(){
        errorMessage.value = R.string.movies_error
        loadingVisibility.value = View.GONE
    }

    private fun onSearchMoviesSuccess(moviesList:ArrayList<MoviesResponse.Result>){
        currentPage = 1
        moviesAdapter.updateSearchList(moviesList, true)
    }

    private fun onSearchMoviesError(){
        errorMessage.value = R.string.movies_search_error
        loadingVisibility.value = View.GONE
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}