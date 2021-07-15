package com.aleksandar.moviedbapp.network

import com.aleksandar.moviedbapp.BuildConfig.BASE_URL
import com.aleksandar.moviedbapp.model.MovieDetailsResponse
import com.aleksandar.moviedbapp.model.MoviesResponse
import io.reactivex.Observable
import retrofit2.http.*

interface API {

    @Headers("Content-Type: application/vnd.api+json")
    @GET(BASE_URL + "trending/{media_type}/{time_window}")
    fun getMovies(
        @Path("media_type") media_type  :String?,
        @Path("time_window") time_window  :String?,
        @Query("api_key") api_key: String?,
        @Query("page") page: Int?)
    : Observable<MoviesResponse>

    @Headers("Content-Type: application/vnd.api+json")
    @GET(BASE_URL + "search/tv")
    fun searchMovies(
        @Query("api_key") api_key: String?,
        @Query("query") query: String?)
    : Observable<MoviesResponse>

    @Headers("Content-Type: application/vnd.api+json")
    @GET(BASE_URL + "tv/{movie_id}")
    fun getDetails(
        @Path("movie_id") movie_id: String?,
        @Query("api_key") api_key: String?)
    : Observable<MovieDetailsResponse>

    @Headers("Content-Type: application/vnd.api+json")
    @GET(BASE_URL + "tv/{movie_id}/similar")
    fun getSimilar(
        @Path("movie_id") movie_id: String?,
        @Query("api_key") api_key: String?)
            : Observable<MoviesResponse>



}

