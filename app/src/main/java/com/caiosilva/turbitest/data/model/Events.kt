package com.caiosilva.turbitest.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Events(
    val available: Int?,
    val collectionURI: String?,
    val items: List<Items>?,
    val returned: Int?
)
