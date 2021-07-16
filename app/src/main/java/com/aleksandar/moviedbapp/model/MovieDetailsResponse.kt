package com.aleksandar.moviedbapp.model


import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.aleksandar.moviedbapp.model.converter.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "details_response")
data class MovieDetailsResponse(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var primaryKey: Int = 0,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @TypeConverters(CreatedByTypeConverter::class)
    @SerializedName("created_by")
    val createdBy: List<CreatedBy> = listOf(),
    @TypeConverters(IntTypeConverter::class)
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int> = listOf(),
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @TypeConverters(GenreTypeConverter::class)
    @SerializedName("genres")
    val genres: List<Genre> = listOf(),
    @SerializedName("homepage")
    val homepage: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("in_production")
    val inProduction: Boolean,
    @TypeConverters(StringTypeConverter::class)
    @SerializedName("languages")
    val languages: List<String> = listOf(),
    @SerializedName("last_air_date")
    val lastAirDate: String,
    @TypeConverters(LastEpisodeTypeConverter::class)
    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir,
    @SerializedName("name")
    val name: String,
    @TypeConverters(NetworkTypeConverter::class)
    @SerializedName("networks")
    val networks: List<Network> = listOf(),
    @TypeConverters(NextEpisodeTypeConverter::class)
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: Any? = Any(),
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int,
    @TypeConverters(StringTypeConverter::class)
    @SerializedName("origin_country")
    val originCountry: List<String> = listOf(),
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @TypeConverters(ProductionCompanyTypeConverter::class)
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany> = listOf(),
    @TypeConverters(ProductionCountryTypeConverter::class)
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry> = listOf(),
    @TypeConverters(SeasonTypeConverter::class)
    @SerializedName("seasons")
    val seasons: List<Season> = listOf(),
    @TypeConverters(SpokenLanguagesTypeConverter::class)
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage> = listOf(),
    @SerializedName("status")
    val status: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    var isFavourite: Boolean = false
) {
    data class CreatedBy(
        @SerializedName("credit_id")
        val creditId: String,
        @SerializedName("gender")
        val gender: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("profile_path")
        val profilePath: String
    )

    data class Genre(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )

    data class LastEpisodeToAir(
        @SerializedName("air_date")
        val airDate: String,
        @SerializedName("episode_number")
        val episodeNumber: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("production_code")
        val productionCode: String,
        @SerializedName("season_number")
        val seasonNumber: Int,
        @SerializedName("still_path")
        val stillPath: String,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
    )

    data class Network(
        @SerializedName("id")
        val id: Int,
        @SerializedName("logo_path")
        val logoPath: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("origin_country")
        val originCountry: String
    )

    data class ProductionCompany(
        @SerializedName("id")
        val id: Int,
        @SerializedName("logo_path")
        val logoPath: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("origin_country")
        val originCountry: String
    )

    data class ProductionCountry(
        @SerializedName("iso_3166_1")
        val iso31661: String,
        @SerializedName("name")
        val name: String
    )

    data class Season(
        @SerializedName("air_date")
        val airDate: String,
        @SerializedName("episode_count")
        val episodeCount: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("season_number")
        val seasonNumber: Int
    )

    data class SpokenLanguage(
        @SerializedName("english_name")
        val englishName: String,
        @SerializedName("iso_639_1")
        val iso6391: String,
        @SerializedName("name")
        val name: String
    )
}