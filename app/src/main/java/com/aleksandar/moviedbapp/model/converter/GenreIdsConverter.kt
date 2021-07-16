package com.aleksandar.moviedbapp.model.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class GenreIdsConverter{
    @TypeConverter fun fromGenreIdList(genreId: List<Int?>?): String? {
        if (genreId == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Int?>?>() {}.type
        return gson.toJson(genreId, type)
    }

    @TypeConverter fun toGenreIdList(genreId: String?): List<Int>? {
        if (genreId == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<Int?>?>() {}.type
        return gson.fromJson<List<Int>>(genreId, type)
    }
}