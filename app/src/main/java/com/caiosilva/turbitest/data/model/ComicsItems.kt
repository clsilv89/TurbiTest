package com.caiosilva.turbitest.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ComicsItems(
    val resourceURI: String,
    val name: String
): Parcelable
