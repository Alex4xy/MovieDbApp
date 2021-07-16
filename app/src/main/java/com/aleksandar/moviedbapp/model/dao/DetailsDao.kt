package com.aleksandar.moviedbapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.aleksandar.moviedbapp.model.MovieDetailsResponse
import com.aleksandar.moviedbapp.model.MoviesResponse


@Dao
interface DetailsDao {
    @get:Query("SELECT * FROM details_response")
    val all: List<MovieDetailsResponse>

    @Query("SELECT * FROM details_response WHERE id = :id")
    fun getById(id: Int?): MovieDetailsResponse

    @Update
    fun update(movieDetailsResponse: MovieDetailsResponse?)

    @Insert
    fun insert(vararg details: MovieDetailsResponse)
}