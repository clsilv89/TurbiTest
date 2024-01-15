package com.caiosilva.turbitest.viewmodel

import androidx.paging.PagingData
import com.caiosilva.turbitest.data.model.Response
import com.caiosilva.turbitest.data.model.ResponseResults
import com.caiosilva.turbitest.data.remote.ResultWrapper
import com.caiosilva.turbitest.rule.MainCoroutineRule
import com.caiosilva.turbitest.usecases.IComicListUseCase
import com.caiosilva.turbitest.view.viewmodel.ComicsListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ComicListViewModelTest {
    private lateinit var viewModel: ComicsListViewModel
    private lateinit var useCase: IComicListUseCase

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = MainCoroutineRule()

    @Before
    fun setUp() {
        useCase = mockk<IComicListUseCase>(relaxed = true)
        viewModel = ComicsListViewModel(useCase)
    }

    @Test
    fun `comic list should return flow pager data`() = runTest {
        val comicList = mockk<PagingData<ResponseResults>>(relaxed = true)
        val response = mockk<ResultWrapper.Success<Response>>(relaxed = true)
        val successResult =
            mockk<ResultWrapper.Success<PagingData<ResponseResults>>>(relaxed = true)
        coEvery {
            successResult.value
        } returns comicList
        coEvery {
            useCase.invoke(any(), any())
        } returns response

        assertEquals(
            comicList,
            successResult.value
        )
    }
}