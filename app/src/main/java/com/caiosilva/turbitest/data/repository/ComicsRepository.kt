package com.caiosilva.turbitest.data.repository

import com.caiosilva.turbitest.data.api.TurbiTestApi
import com.caiosilva.turbitest.data.remote.NetworkHelper
import kotlinx.coroutines.Dispatchers

class ComicsRepository(
    private val api: TurbiTestApi,
    private val networkHelp: NetworkHelper
) {
    private val dispatcher = Dispatchers.IO

    suspend fun getComics(
        apikey: String,
        timeStamp: String,
        hash: String,
        limit: Int,
        offset: Int
    ) =
        networkHelp.safeApiCall(dispatcher) {
            api.getComics(
                apikey = apikey,
                ts = timeStamp,
                hash = hash,
                limit = limit,
                offset = offset
            )
        }

    suspend fun getComicDetails(
        apikey: String,
        timeStamp: String,
        hash: String,
        comicId: Int
    ) =
        networkHelp.safeApiCall(dispatcher) {
            api.getComicDetails(
                apikey = apikey,
                ts = timeStamp,
                hash = hash,
                comicId = comicId
            )
        }
}