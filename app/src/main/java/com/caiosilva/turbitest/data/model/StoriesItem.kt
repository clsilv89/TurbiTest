package com.caiosilva.turbitest.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoriesItem(
    val name: String?,
    val resourceURI: String?,
    val type: String?
)
