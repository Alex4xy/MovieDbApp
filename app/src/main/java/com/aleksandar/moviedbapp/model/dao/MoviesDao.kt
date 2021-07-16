package com.aleksandar.moviedbapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aleksandar.moviedbapp.model.MoviesResponse


@Dao
interface MoviesDao {
    @get:Query("SELECT * FROM movies_response")
    val all: List<MoviesResponse>

    @get:Query("SELECT * FROM movies_response WHERE page = (SELECT MAX(page) FROM movies_response)")
    val lastPage: Int

    @Insert
    fun insertAll(vararg movies: MoviesResponse)
}