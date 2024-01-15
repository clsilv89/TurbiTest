package com.caiosilva.turbitest.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    val error_description: String,
    val detailMessage: String,
    val causes: Map<String, String> = emptyMap(),
    val message: String
)