package com.caiosilva.turbitest.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caiosilva.turbitest.data.remote.ResultWrapper
import com.caiosilva.turbitest.usecases.IComicDetailsUseCase
import com.caiosilva.turbitest.view.viewstates.ComicDetailsViewState
import kotlinx.coroutines.launch

class ComicDetailsViewModel(
    private val comicDetailsUseCase: IComicDetailsUseCase
) : ViewModel() {
    private val _comicDetailsLiveData = MutableLiveData<ComicDetailsViewState>()
    val comicDetailsLiveData: LiveData<ComicDetailsViewState> = _comicDetailsLiveData

    fun getComicDetails(comicId: Int) {
        ComicDetailsViewState.OnLoading.run()
        viewModelScope.launch {
            when (val result = comicDetailsUseCase.invoke(comicId)) {
                is ResultWrapper.Success -> {
                    ComicDetailsViewState.OnSuccess(result.value).run()
                }

                is ResultWrapper.GenericError -> {
                    ComicDetailsViewState.OnError(result.error?.detailMessage.orEmpty()).run()
                }

                is ResultWrapper.NetworkError -> {
                    ComicDetailsViewState.OnError("Network error").run()
                }
            }
        }
    }

    private fun ComicDetailsViewState.run() {
        _comicDetailsLiveData.postValue(this)
    }
}