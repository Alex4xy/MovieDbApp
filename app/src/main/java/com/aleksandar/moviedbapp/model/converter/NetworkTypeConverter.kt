package com.aleksandar.moviedbapp.model.converter

import androidx.room.TypeConverter
import com.aleksandar.moviedbapp.model.MovieDetailsResponse
import com.aleksandar.moviedbapp.model.MoviesResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class NetworkTypeConverter{

    @TypeConverter fun fromList(string: List<MovieDetailsResponse.Network?>?): String? {
        if (string == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<MovieDetailsResponse.Network?>?>() {}.type
        return gson.toJson(string, type)
    }

    @TypeConverter fun toList(string: String?): List<MovieDetailsResponse.Network>? {
        if (string == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<MovieDetailsResponse.Network?>?>() {}.type
        return gson.fromJson<List<MovieDetailsResponse.Network>>(string, type)
    }

}