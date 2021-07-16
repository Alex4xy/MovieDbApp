package com.aleksandar.moviedbapp.model.converter

import androidx.room.TypeConverter
import com.aleksandar.moviedbapp.model.MovieDetailsResponse
import com.aleksandar.moviedbapp.model.MoviesResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SpokenLanguagesTypeConverter{

    @TypeConverter fun fromList(string: List<MovieDetailsResponse.SpokenLanguage?>?): String? {
        if (string == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<MovieDetailsResponse.SpokenLanguage?>?>() {}.type
        return gson.toJson(string, type)
    }

    @TypeConverter fun toList(string: String?): List<MovieDetailsResponse.SpokenLanguage>? {
        if (string == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<MovieDetailsResponse.SpokenLanguage?>?>() {}.type
        return gson.fromJson<List<MovieDetailsResponse.SpokenLanguage>>(string, type)
    }

}