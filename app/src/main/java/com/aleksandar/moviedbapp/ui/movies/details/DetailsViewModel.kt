package com.aleksandar.moviedbapp.ui.movies.details

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.aleksandar.moviedbapp.BuildConfig
import com.aleksandar.moviedbapp.R
import com.aleksandar.moviedbapp.base.BaseViewModel
import com.aleksandar.moviedbapp.model.MovieDetailsResponse
import com.aleksandar.moviedbapp.model.MoviesResponse
import com.aleksandar.moviedbapp.network.API
import com.aleksandar.moviedbapp.util.TMDB_API_KEY
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailsViewModel : BaseViewModel() {
    @Inject
    lateinit var api: API
    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    private val detailImageUrl = MutableLiveData<String>()
    val movieTitle: MutableLiveData<String> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val similarShowsAdapter: SimilarShowsAdapter = SimilarShowsAdapter()

    fun getMovieDetails(id: String?) {
        subscription = api.getDetails(id, TMDB_API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                onGetMovieDetailsStart()
            }
            .doOnTerminate {
                onGetMovieDetailsFinish()
            }
            .subscribe(
                {
                    onGetMovieDetailsSuccess(it)
                },
                {
                    onGetMovieDetailsError()
                }
                      )
    }

    fun getSimilar(id: String?) {
        subscription = api.getSimilar(id, TMDB_API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                onGetSimilarStart()
            }
            .doOnTerminate {
                onGetSimilarFinish()
            }
            .subscribe(
                {
                    onGetSimilarSuccess(it.results)
                },
                {
                    onGetSimilarError()
                }
                      )
    }

    private fun onGetMovieDetailsStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onGetMovieDetailsFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onGetMovieDetailsSuccess(movieDetails:MovieDetailsResponse){
        movieTitle.value = movieDetails.originalName

        val posterPath = movieDetails.posterPath
        detailImageUrl.value = BuildConfig.POSTER_W300_URL + posterPath
    }

    fun getDetailImageUrl():MutableLiveData<String>{
        return detailImageUrl
    }

    private fun onGetMovieDetailsError(){
        errorMessage.value = R.string.movies_error
        loadingVisibility.value = View.GONE
    }

    private fun onGetSimilarStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onGetSimilarFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onGetSimilarSuccess(moviesList:ArrayList<MoviesResponse.Result>){
        similarShowsAdapter.updateSimilarList(moviesList)
    }

    private fun onGetSimilarError(){
        errorMessage.value = R.string.movies_error
        loadingVisibility.value = View.GONE
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}