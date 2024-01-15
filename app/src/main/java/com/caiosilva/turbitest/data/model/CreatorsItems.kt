package com.caiosilva.turbitest.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreatorsItems(
    val resourceURI: String?,
    val name: String?,
    val role: String?
)
