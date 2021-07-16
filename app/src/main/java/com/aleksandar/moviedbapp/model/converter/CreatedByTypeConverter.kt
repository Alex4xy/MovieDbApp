package com.aleksandar.moviedbapp.model.converter

import androidx.room.TypeConverter
import com.aleksandar.moviedbapp.model.MovieDetailsResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class CreatedByTypeConverter{

    @TypeConverter fun fromList(string: List<MovieDetailsResponse.CreatedBy?>?): String? {
        if (string == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<MovieDetailsResponse.CreatedBy?>?>() {}.type
        return gson.toJson(string, type)
    }

    @TypeConverter fun toList(string: String?): List<MovieDetailsResponse.CreatedBy>? {
        if (string == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<MovieDetailsResponse.CreatedBy?>?>() {}.type
        return gson.fromJson<List<MovieDetailsResponse.CreatedBy>>(string, type)
    }

}