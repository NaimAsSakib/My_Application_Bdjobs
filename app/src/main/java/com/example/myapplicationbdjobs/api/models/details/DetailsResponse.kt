package com.example.myapplicationbdjobs.api.models.details

import com.squareup.moshi.Json

data class DetailsResponse(

    @field:Json(name = "original_language")
    val originalLanguage: String? = null,

    @field:Json(name = "imdb_id")
    val imdbId: String? = null,

    @field:Json(name = "video")
    val video: Boolean? = null,

    @field:Json(name = "title")
    val title: String? = null,

    @field:Json(name = "backdrop_path")
    val backdropPath: String? = null,

    @field:Json(name = "revenue")
    val revenue: Int? = null,

    @field:Json(name = "genres")
    val genres: List<GenresItem?>? = null,

    @field:Json(name = "popularity")
    val popularity: Any? = null,

    @field:Json(name = "production_countries")
    val productionCountries: List<ProductionCountriesItem?>? = null,

    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "vote_count")
    val voteCount: Int? = null,

    @field:Json(name = "budget")
    val budget: Int? = null,

    @field:Json(name = "overview")
    val overview: String? = null,

    @field:Json(name = "original_title")
    val originalTitle: String? = null,

    @field:Json(name = "runtime")
    val runtime: Int? = null,

    @field:Json(name = "poster_path")
    val posterPath: String? = null,

    @field:Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguagesItem?>? = null,

    @field:Json(name = "production_companies")
    val productionCompanies: List<ProductionCompaniesItem?>? = null,

    @field:Json(name = "release_date")
    val releaseDate: String? = null,

    @field:Json(name = "vote_average")
    val voteAverage: Any? = null,

    @field:Json(name = "belongs_to_collection")
    val belongsToCollection: BelongsToCollection? = null,

    @field:Json(name = "tagline")
    val tagline: String? = null,

    @field:Json(name = "adult")
    val adult: Boolean? = null,

    @field:Json(name = "homepage")
    val homepage: String? = null,

    @field:Json(name = "status")
    val status: String? = null,

    var isBookmarked: Boolean = false
)

data class GenresItem(

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "id")
    val id: Int? = null
)

data class BelongsToCollection(

    @field:Json(name = "backdrop_path")
    val backdropPath: String? = null,

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "poster_path")
    val posterPath: String? = null
)

data class ProductionCountriesItem(

    @field:Json(name = "iso_3166_1")
    val iso31661: String? = null,

    @field:Json(name = "name")
    val name: String? = null
)

data class ProductionCompaniesItem(

    @field:Json(name = "logo_path")
    val logoPath: String? = null,

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "id")
    val id: Int? = null,

    @field:Json(name = "origin_country")
    val originCountry: String? = null
)

data class SpokenLanguagesItem(

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "iso_639_1")
    val iso6391: String? = null,

    @field:Json(name = "english_name")
    val englishName: String? = null
)
