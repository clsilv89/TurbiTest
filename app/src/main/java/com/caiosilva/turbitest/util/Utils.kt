package com.caiosilva.turbitest.util

import android.app.Activity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.math.BigInteger
import java.security.MessageDigest

fun String.toMD5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(this.toByteArray())).toString(16).padStart(32, '0')
}

inline fun <reified T> Moshi.objectToJson(data: T): String =
    adapter(T::class.java).toJson(data)

inline fun <reified T> Moshi.jsonToObject(json: String): T? =
    adapter(T::class.java).fromJson(json)
