package com.aleksandar.moviedbapp.model.converter

import androidx.room.TypeConverter
import com.aleksandar.moviedbapp.model.MovieDetailsResponse
import com.aleksandar.moviedbapp.model.MoviesResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class LastEpisodeTypeConverter{

    @TypeConverter fun fromMoviesList(string: MovieDetailsResponse.LastEpisodeToAir?): String? {
        if (string == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<MovieDetailsResponse.LastEpisodeToAir?>() {}.type
        return gson.toJson(string, type)
    }

    @TypeConverter fun toMoviesList(string: String?): MovieDetailsResponse.LastEpisodeToAir? {
        if (string == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<MovieDetailsResponse.LastEpisodeToAir?>() {}.type
        return gson.fromJson<MovieDetailsResponse.LastEpisodeToAir>(string, type)
    }

}