package com.caiosilva.turbitest.usecases

import com.caiosilva.turbitest.data.model.Response
import com.caiosilva.turbitest.data.remote.ResultWrapper

interface IComicDetailsUseCase {
    suspend fun invoke(comicId: Int): ResultWrapper<Response>
}