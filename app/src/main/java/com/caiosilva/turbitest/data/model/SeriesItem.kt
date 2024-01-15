package com.caiosilva.turbitest.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeriesItem(
    val resourceURI: String?,
    val name: String?
)
