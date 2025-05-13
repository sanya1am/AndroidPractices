package com.sanya1am.impl.di

import com.sanya1am.api.domain.repository.IMoviesRepositoty
import com.sanya1am.impl.data.mapper.MovieResponseToEntityMapper
import com.sanya1am.impl.data.repository.MoviesRepository
import com.sanya1am.impl.presentation.viewModel.DetailsViewModel
import com.sanya1am.impl.presentation.viewModel.ListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val moviesModule = module {

    single<IMoviesRepositoty> { MoviesRepository(get(), get(), get()) }

    factory { MovieResponseToEntityMapper() }

    viewModel { ListViewModel(get(), it.get()) }
    viewModel { DetailsViewModel(get(), it.get(), it.get()) }
}