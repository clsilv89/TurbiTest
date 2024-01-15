package com.caiosilva.turbitest.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Comics(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<ComicsItems>? = null,
    val series: List<ComicsItems>? = null,
    val stories: List<ComicsItems>? = null,
    val events: List<ComicsItems>? = null,
):Parcelable
