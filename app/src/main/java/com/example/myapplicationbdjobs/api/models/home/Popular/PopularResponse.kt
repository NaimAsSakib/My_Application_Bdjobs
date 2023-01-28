package com.example.myapplicationbdjobs.api.models

import com.squareup.moshi.Json

data class PopularResponse(

	@Json(name="page")
	val page: Int? = null,

	@Json(name="total_pages")
	val totalPages: Int? = null,

	@Json(name="results")
	val results: List<ResultsItem>? = null,

	@Json(name="total_results")
	val totalResults: Int? = null
)

data class ResultsItem(

	@Json(name="overview")
	val overview: String? = null,

	@field:Json(name="original_language")
	val originalLanguage: String? = null,

	@field:Json(name="original_title")
	val originalTitle: String? = null,

	@Json(name="video")
	val video: Boolean? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="genre_ids")
	val genreIds: List<Int?>? = null,

	@field:Json(name="poster_path")
	val posterPath: String? = null,

	@field:Json(name="backdrop_path")
	val backdropPath: String? = null,

	@Json(name="release_date")
	val releaseDate: String? = null,

	@Json(name="popularity")
	val popularity: Any? = null,

	@field:Json(name="vote_average")
	val voteAverage: Any? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="adult")
	val adult: Boolean? = null,

	@field:Json(name="vote_count")
	val voteCount: Int? = null
)
