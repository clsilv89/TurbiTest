package com.caiosilva.turbitest.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.caiosilva.turbitest.data.datasource.ComicsListDataSource
import com.caiosilva.turbitest.usecases.IComicListUseCase

class ComicsListViewModel(
    private val characterListUseCase: IComicListUseCase
) : ViewModel() {

    val comicsList = Pager(PagingConfig(20)) {
        ComicsListDataSource(characterListUseCase)
    }.flow.cachedIn(viewModelScope)
}
