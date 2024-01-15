package com.caiosilva.turbitest.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Items(
    val name: String?,
    val resourceURI: String?
)
