package com.aleksandar.moviedbapp.ui.movies.landing

import android.database.sqlite.SQLiteConstraintException
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.aleksandar.moviedbapp.R
import com.aleksandar.moviedbapp.base.BaseViewModel
import com.aleksandar.moviedbapp.model.MoviesResponse
import com.aleksandar.moviedbapp.model.dao.MoviesDao
import com.aleksandar.moviedbapp.network.API
import com.aleksandar.moviedbapp.util.MEDIA_TYPE_TV
import com.aleksandar.moviedbapp.util.TIME_WINDOW_WEEK
import com.aleksandar.moviedbapp.util.TMDB_API_KEY
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesLandingViewModel(private val moviesDao: MoviesDao) : BaseViewModel() {

    @Inject
    lateinit var api: API
    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { getMovies(false) }
    val moviesAdapter: MoviesAdapter = MoviesAdapter()
    var currentPage = 1
    var totalAvailablePages = 1
    var isSearching: Boolean = false
    var isScrolling: Boolean = false

    fun getMovies(isConnected: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            val lastPage = moviesDao.lastPage
            if(lastPage != 0){
            currentPage = lastPage + 1
            }

        }
        subscription = Observable.fromCallable { moviesDao.all }
            .concatMap {
                    dbMoviesList ->
                if((dbMoviesList.isEmpty() || isScrolling) && isConnected)
                    api.getMovies(MEDIA_TYPE_TV, TIME_WINDOW_WEEK, TMDB_API_KEY, currentPage).concatMap {
                            movies ->
                        if(moviesDao.all.containsAll(listOf(movies))){
                            Observable.just(movies)
                        }else{
                            try{
                                moviesDao.insertAll(movies)
                            }catch (e: SQLiteConstraintException){
                                e.printStackTrace()
                            }
                        Observable.just(movies)
                        }
                    }
                else{
                    Observable.just(dbMoviesList.distinctBy { it.primaryKey })
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                onGetMoviesStart()
            }
            .doOnTerminate {
                onGetMoviesFinish()
            }
            .subscribe(
                { result ->
                    when (result) {
                        is MoviesResponse -> {
                            totalAvailablePages = result.totalPages
                            onGetMoviesSuccess(result.results)
                        }
                        is ArrayList<*> -> {
                            val moviesList = result as ArrayList<MoviesResponse>
                            val resultList : ArrayList<MoviesResponse.Result> = arrayListOf()
                            for(item in moviesList){
                                totalAvailablePages = item.totalPages
                                resultList.addAll(item.results)
                            }
                            onGetMoviesSuccess(resultList)
                        }
                        is List<*> ->{
                            val moviesList = result as List<MoviesResponse>
                            val resultList : ArrayList<MoviesResponse.Result> = arrayListOf()
                            for(item in moviesList){
                                totalAvailablePages = item.totalPages
                                resultList.addAll(item.results)
                            }
                            onGetMoviesSuccess(resultList)
                        }
                        else -> {
                            onGetMoviesError()
                        }
                    }

                },
                {
                    onGetMoviesError() })
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
        isScrolling = false
    }

    private fun onGetMoviesSuccess(moviesList: ArrayList<MoviesResponse.Result>){
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