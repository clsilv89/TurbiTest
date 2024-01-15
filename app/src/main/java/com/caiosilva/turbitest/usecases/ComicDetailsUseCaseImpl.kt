package com.caiosilva.turbitest.usecases

import com.caiosilva.turbitest.BuildConfig
import com.caiosilva.turbitest.data.model.Response
import com.caiosilva.turbitest.data.remote.ResultWrapper
import com.caiosilva.turbitest.data.repository.ComicsRepository
import com.caiosilva.turbitest.util.toMD5

class ComicDetailsUseCaseImpl(
    private val repository: ComicsRepository
) : IComicDetailsUseCase {
    override suspend fun invoke(comicId: Int): ResultWrapper<Response> {
        val timestamp = System.currentTimeMillis().toString()
        val privateKey = BuildConfig.PRIVATE_KEY
        val publicKey = BuildConfig.PUBLIC_KEY

        return repository.getComicDetails(
            apikey = publicKey,
            timeStamp = timestamp,
            hash = (timestamp + privateKey + publicKey).toMD5(),
            comicId = comicId
        )
    }
}