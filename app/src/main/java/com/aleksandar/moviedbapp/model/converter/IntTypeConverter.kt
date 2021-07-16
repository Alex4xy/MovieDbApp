package com.aleksandar.moviedbapp.model.converter

import androidx.room.TypeConverter
import com.aleksandar.moviedbapp.model.MoviesResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class IntTypeConverter{

    @TypeConverter fun fromList(string: List<Int?>?): String? {
        if (string == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Int?>?>() {}.type
        return gson.toJson(string, type)
    }

    @TypeConverter fun toList(string: String?): List<Int>? {
        if (string == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Int?>?>() {}.type
        return gson.fromJson<List<Int>>(string, type)
    }

}