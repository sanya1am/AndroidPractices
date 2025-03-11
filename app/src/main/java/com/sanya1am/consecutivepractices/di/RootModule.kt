package com.sanya1am.consecutivepractices.di

import com.sanya1am.consecutivepractices.listWithDetails.data.repository.MoviesRepository
import com.sanya1am.consecutivepractices.listWithDetails.presentation.viewModel.DetailsViewModel
import com.sanya1am.consecutivepractices.listWithDetails.presentation.viewModel.ListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val rootModule = module {
    single<MoviesRepository> { MoviesRepository() }

    viewModel { ListViewModel(get(), it.get()) }
    viewModel { DetailsViewModel(get(), it.get(), it.get()) }
}