package com.caiosilva.turbitest.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Creators(
    val available: Int?,
    val collectionURI: String?,
    val items: List<CreatorsItems>?,
    val returned: Int?
)
