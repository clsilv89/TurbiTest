package com.caiosilva.turbitest.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TextObjects (
    val type: String?,
    val language: String?,
    val text: String?
)
