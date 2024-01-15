package com.caiosilva.turbitest.data.api

import com.caiosilva.turbitest.data.model.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TurbiTestApi {
    @GET("v1/public/comics")
    suspend fun getComics(
        @Query("apikey") apikey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response

    suspend fun getSearchedComics(
        @Query("apikey") apikey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response

    @GET("v1/public/comics/{comicId}")
    suspend fun getComicDetails(
        @Path("comicId") comicId: Int,
        @Query("apikey") apikey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String
    ): Response
}