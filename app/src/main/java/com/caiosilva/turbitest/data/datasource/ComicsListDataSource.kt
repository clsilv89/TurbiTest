package com.caiosilva.turbitest.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.caiosilva.turbitest.data.model.ResponseResults
import com.caiosilva.turbitest.data.remote.ResultWrapper
import com.caiosilva.turbitest.usecases.IComicListUseCase

class ComicsListDataSource(
    private val characterListUseCase: IComicListUseCase
) : PagingSource<Int, ResponseResults>() {
    override fun getRefreshKey(state: PagingState<Int, ResponseResults>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(
                anchorPosition
            )
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseResults> {
        val page = params.key ?: 0
        val offset = page * PAGE_SIZE
        var nextKey: Int? = 0
        val response = characterListUseCase.invoke(LIMIT, offset)
        val responseData = mutableListOf<ResponseResults>()
        try {
            when (response) {
                is ResultWrapper.Success -> {
                    val data = response.value.data?.results
                    nextKey =
                        if (offset >= (response.value.data?.total ?: 0)) null
                        else page + 1
                    responseData.addAll(data.orEmpty())
                }

                is ResultWrapper.GenericError, ResultWrapper.NetworkError -> {
                    return LoadResult.Error(Throwable(response.toString()))
                }
            }
            return LoadResult.Page(
                data = responseData,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object {
        const val PAGE_SIZE = 20
        const val LIMIT = 20
    }

}