package com.caiosilva.turbitest.usecases

import com.caiosilva.turbitest.data.model.Response
import com.caiosilva.turbitest.data.remote.ResultWrapper

interface IComicListUseCase {
    suspend fun invoke(limit: Int, offset: Int): ResultWrapper<Response>
}