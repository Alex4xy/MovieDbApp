package com.aleksandar.moviedbapp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aleksandar.moviedbapp.model.MoviesResponse
import com.aleksandar.moviedbapp.model.converter.ResultConverter
import com.aleksandar.moviedbapp.model.converter.GenreIdsConverter
import com.aleksandar.moviedbapp.model.dao.MoviesDao


@Database(entities = [MoviesResponse::class], version = 1)
@TypeConverters(ResultConverter::class, GenreIdsConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao

}