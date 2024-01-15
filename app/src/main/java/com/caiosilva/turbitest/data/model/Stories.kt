package com.caiosilva.turbitest.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stories(
    val available: Int?,
    val collectionURI: String?,
    val items: List<StoriesItem>?,
    val returned: Int?
)
