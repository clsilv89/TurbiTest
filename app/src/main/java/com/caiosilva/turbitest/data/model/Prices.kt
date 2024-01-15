package com.caiosilva.turbitest.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Prices(
    val type: String?,
    val price: Double?
)
