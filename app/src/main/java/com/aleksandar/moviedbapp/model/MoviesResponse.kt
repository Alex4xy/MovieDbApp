package com.aleksandar.moviedbapp.model


import androidx.annotation.NonNull
import androidx.room.*
import com.aleksandar.moviedbapp.model.converter.ResultConverter
import com.aleksandar.moviedbapp.model.converter.GenreIdsConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies_response", indices = [Index(value = ["page", "results", "total_pages", "total_results"], unique = true)])
data class MoviesResponse(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var primaryKey: Int = 0,
    @ColumnInfo(name = "page")
    @SerializedName("page")
    var page: Int,
    @TypeConverters(ResultConverter::class)
    @ColumnInfo(name = "results")
    @SerializedName("results")
    var results: ArrayList<Result> = arrayListOf(),
    @ColumnInfo(name = "total_pages")
    @SerializedName("total_pages")
    var totalPages: Int,
    @ColumnInfo(name = "total_results")
    @SerializedName("total_results")
    var totalResults: Int
){
    data class Result(
        @SerializedName("adult")
        var adult: Boolean?,
        @SerializedName("backdrop_path")
        var backdropPath: String?,
        @TypeConverters(GenreIdsConverter::class)
        @SerializedName("genre_ids")
        @ColumnInfo(name = "genre_ids_list")
        var genreIds: List<Int> = listOf(),
        @SerializedName("id")
        var id: Int?,
        @SerializedName("media_type")
        var mediaType: String?,
        @SerializedName("original_language")
        var originalLanguage: String?,
        @SerializedName("original_title")
        var originalTitle: String?,
        @SerializedName("overview")
        var overview: String?,
        @SerializedName("popularity")
        var popularity: Double?,
        @SerializedName("poster_path")
        var posterPath: String?,
        @SerializedName("release_date")
        var releaseDate: String?,
        @SerializedName("title")
        var title: String?,
        @SerializedName("original_name")
        var originalName: String?,
        @SerializedName("video")
        var video: Boolean?,
        @SerializedName("vote_average")
        var voteAverage: Double?,
        @SerializedName("vote_count")
        var voteCount: Int?
                     )
}