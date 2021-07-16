package com.aleksandar.moviedbapp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aleksandar.moviedbapp.model.MovieDetailsResponse
import com.aleksandar.moviedbapp.model.MoviesResponse
import com.aleksandar.moviedbapp.model.converter.*
import com.aleksandar.moviedbapp.model.dao.DetailsDao
import com.aleksandar.moviedbapp.model.dao.MoviesDao


@Database(entities = [MoviesResponse::class, MovieDetailsResponse::class], version = 1)
@TypeConverters(ResultConverter::class,  CreatedByTypeConverter::class,  IntTypeConverter::class, StringTypeConverter::class, GenreTypeConverter::class, NetworkTypeConverter::class, ProductionCompanyTypeConverter::class, ProductionCountryTypeConverter::class, SeasonTypeConverter::class, SpokenLanguagesTypeConverter::class, LastEpisodeTypeConverter::class, NextEpisodeTypeConverter::class )
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun detailsDao(): DetailsDao

}