package com.aleksandar.moviedbapp.model.converter

import androidx.room.TypeConverter
import com.aleksandar.moviedbapp.model.MovieDetailsResponse
import com.aleksandar.moviedbapp.model.MoviesResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class NextEpisodeTypeConverter{

    @TypeConverter fun fromMoviesList(string: Any?): String? {
        if (string == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<Any?>() {}.type
        return gson.toJson(string, type)
    }

    @TypeConverter fun toMoviesList(string: String?): Any? {
        if (string == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<Any?>() {}.type
        return gson.fromJson<Any>(string, type)
    }

}