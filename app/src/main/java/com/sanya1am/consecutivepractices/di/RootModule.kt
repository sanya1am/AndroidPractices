package com.sanya1am.consecutivepractices.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.sanya1am.consecutivepractices.listWithDetails.data.mapper.MovieResponseToEntityMapper
import com.sanya1am.consecutivepractices.listWithDetails.data.repository.MoviesRepository
import com.sanya1am.consecutivepractices.listWithDetails.presentation.viewModel.DetailsViewModel
import com.sanya1am.consecutivepractices.listWithDetails.presentation.viewModel.ListViewModel
import com.sanya1am.consecutivepractices.profile.presentation.viewModel.ProfileViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val rootModule = module {

    single { getDataStore(androidContext = androidContext()) }

    single<MoviesRepository> { MoviesRepository(get(), get(), get()) }

    factory { MovieResponseToEntityMapper() }

    viewModel { ListViewModel(get(), it.get()) }
    viewModel { DetailsViewModel(get(), it.get(), it.get()) }
    viewModel { ProfileViewModel(get()) }
}

fun getDataStore(androidContext: Context): DataStore<Preferences> =
    PreferenceDataStoreFactory.create {
        androidContext.preferencesDataStoreFile("default")
    }