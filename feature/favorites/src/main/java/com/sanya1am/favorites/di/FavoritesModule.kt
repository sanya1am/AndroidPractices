package com.sanya1am.favorites.di

import com.sanya1am.favorites.presentation.viewModel.FavoritesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val favoritesModule = module {
    viewModel { FavoritesViewModel(get()) }
}