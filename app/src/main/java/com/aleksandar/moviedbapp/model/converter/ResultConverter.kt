package com.aleksandar.moviedbapp.model.converter

import androidx.room.TypeConverter
import com.aleksandar.moviedbapp.model.MoviesResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ResultConverter{

    @TypeConverter fun fromMoviesList(string: ArrayList<MoviesResponse.Result?>?): String? {
        if (string == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<MoviesResponse.Result?>?>() {}.type
        return gson.toJson(string, type)
    }

    @TypeConverter fun toMoviesList(string: String?): ArrayList<MoviesResponse.Result>? {
        if (string == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<MoviesResponse.Result?>?>() {}.type
        return gson.fromJson<ArrayList<MoviesResponse.Result>>(string, type)
    }

}