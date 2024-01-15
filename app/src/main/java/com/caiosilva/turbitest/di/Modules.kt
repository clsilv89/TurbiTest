package com.caiosilva.turbitest.di

import com.caiosilva.turbitest.BuildConfig
import com.caiosilva.turbitest.data.api.TurbiTestApi
import com.caiosilva.turbitest.data.remote.NetworkHelper
import com.caiosilva.turbitest.data.remote.RestConfig
import com.caiosilva.turbitest.data.repository.ComicsRepository
import com.caiosilva.turbitest.usecases.ComicDetailsUseCaseImpl
import com.caiosilva.turbitest.usecases.ComicListUseCaseImpl
import com.caiosilva.turbitest.usecases.IComicDetailsUseCase
import com.caiosilva.turbitest.usecases.IComicListUseCase
import com.caiosilva.turbitest.view.viewmodel.ComicDetailsViewModel
import com.caiosilva.turbitest.view.viewmodel.ComicsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Modules {
    val module = module {
        factory<TurbiTestApi> { RestConfig.service(BuildConfig.BASE_URL) }
        single { NetworkHelper }
        single { ComicsRepository(get(), get()) }
        single<IComicListUseCase> { ComicListUseCaseImpl(get()) }
        single<IComicDetailsUseCase> { ComicDetailsUseCaseImpl(get()) }
        viewModel { ComicsListViewModel(get()) }
        viewModel { ComicDetailsViewModel(get()) }
    }
}