package com.caiosilva.turbitest.viewmodel

import com.caiosilva.turbitest.data.model.Response
import com.caiosilva.turbitest.data.remote.ResultWrapper
import com.caiosilva.turbitest.rule.MainCoroutineRule
import com.caiosilva.turbitest.usecases.IComicDetailsUseCase
import com.caiosilva.turbitest.view.viewmodel.ComicDetailsViewModel
import com.caiosilva.turbitest.view.viewstates.ComicDetailsViewState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ComicDetailsViewModelTest {
    private lateinit var viewModel: ComicDetailsViewModel
    private lateinit var useCase: IComicDetailsUseCase

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = MainCoroutineRule()

    @Before
    fun setUp() {
        useCase = mockk<IComicDetailsUseCase>(relaxed = true)
        viewModel = ComicDetailsViewModel(useCase)
    }

    @Test
    fun `getComicDetails with success result`() = runTest {
        val comicId = mockk<Int>(relaxed = true)
        val comicDetails = mockk<Response>()
        val successResult = mockk<ResultWrapper.Success<Response>>(relaxed = true)
        coEvery {
            successResult.value
        } returns comicDetails
        coEvery {
            useCase.invoke(comicId)
        } returns successResult

        viewModel.getComicDetails(comicId)

        assertEquals(
            ComicDetailsViewState.OnSuccess(comicDetails).comic,
            successResult.value
        )
    }

    @Test
    fun `getComicDetails with error result`() = runTest {
        val comicId = mockk<Int>(relaxed = true)
        val errorResult = mockk<ResultWrapper.GenericError>(relaxed = true)
        coEvery {
            useCase.invoke(comicId)
        } returns errorResult

        viewModel.getComicDetails(comicId)

        assertEquals(
            ComicDetailsViewState.OnError(errorResult.error?.detailMessage.orEmpty()).error,
            errorResult.error?.detailMessage.orEmpty()
        )
    }
}