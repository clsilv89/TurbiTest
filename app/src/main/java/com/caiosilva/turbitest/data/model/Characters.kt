package com.caiosilva.turbitest.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Characters(
    val id: Int?,
    val name: String?,
    val description: String?,
    val thumbnail: Thumbnail?
)
