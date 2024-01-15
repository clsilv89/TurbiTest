package com.caiosilva.turbitest.usecases

import com.caiosilva.turbitest.data.model.ErrorResponse
import com.caiosilva.turbitest.data.model.Response
import com.caiosilva.turbitest.data.remote.ResultWrapper
import com.caiosilva.turbitest.data.repository.ComicsRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ComicDetailsUseCaseTest {

    private val comicsRepository = mockk<ComicsRepository>()
    private val comicDetailsUseCaseImpl = ComicDetailsUseCaseImpl(
        repository = comicsRepository
    )
    private val response = mockk<Response>()
    private val error = mockk<ErrorResponse>()

    @Test
    fun `when invoke use case should return success`() = runBlocking {
        val success = ResultWrapper.Success(response)
        coEvery {
            comicsRepository.getComicDetails(any(), any(), any(), any())
        } returns ResultWrapper.Success(response)
        val result = comicDetailsUseCaseImpl.invoke(1)
        assertEquals(success, result)
    }

    @Test
    fun `when invoke use case should return error`() = runBlocking {
        val error = ResultWrapper.GenericError(500, error)
        coEvery {
            comicsRepository.getComicDetails(any(), any(), any(), any())
        } returns ResultWrapper.GenericError(500, error.error)
        val result = comicDetailsUseCaseImpl.invoke(1)
        assertEquals(error, result)
    }

    @Test
    fun `when invoke use case should return network error`() = runBlocking {
        val networkError = ResultWrapper.NetworkError
        coEvery {
            comicsRepository.getComicDetails(any(), any(), any(), any())
        } returns ResultWrapper.NetworkError
        val result = comicDetailsUseCaseImpl.invoke(1)
        assertEquals(networkError, result)
    }
}