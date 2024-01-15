package com.caiosilva.turbitest.view.viewstates

import com.caiosilva.turbitest.data.model.Response

sealed class ComicDetailsViewState {
    data class OnSuccess(val comic: Response) :
        ComicDetailsViewState()

    data class OnError(val error: String) : ComicDetailsViewState()
    data object OnLoading : ComicDetailsViewState()
}
