package com.caiosilva.turbitest.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Variants(
    val resourceUri: String?,
    val name: String?
)
