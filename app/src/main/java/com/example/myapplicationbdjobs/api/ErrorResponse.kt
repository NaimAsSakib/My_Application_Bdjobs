package com.example.myapplicationbdjobs.api

import com.squareup.moshi.Json

data class ErrorResponse(

	@field:Json(name="status_message")
	val statusMessage: String? = null,

	@field:Json(name="status_code")
	val statusCode: Int? = null,

	@field:Json(name="success")
	val success: Boolean? = null
)
