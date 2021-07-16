package com.aleksandar.moviedbapp.model.converter

import androidx.room.TypeConverter
import com.aleksandar.moviedbapp.model.MoviesResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ResultConverter{

    @TypeConverter fun fromMoviesList(countryLang: ArrayList<MoviesResponse.Result?>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<MoviesResponse.Result?>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter fun toMoviesList(countryLangString: String?): ArrayList<MoviesResponse.Result>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<MoviesResponse.Result?>?>() {}.type
        return gson.fromJson<ArrayList<MoviesResponse.Result>>(countryLangString, type)
    }

}