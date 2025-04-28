package com.sanya1am.consecutivepractices.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.sanya1am.consecutivepractices.listWithDetails.data.mapper.MovieResponseToEntityMapper
import com.sanya1am.consecutivepractices.listWithDetails.data.repository.MoviesRepository
import com.sanya1am.consecutivepractices.listWithDetails.presentation.viewModel.DetailsViewModel
import com.sanya1am.consecutivepractices.listWithDetails.presentation.viewModel.ListViewModel
import com.sanya1am.consecutivepractices.favorites.presentation.viewModel.FavoritesViewModel
import com.sanya1am.consecutivepractices.profile.data.datastore.DataSourceProvider
import com.sanya1am.consecutivepractices.profile.data.repository.ProfileRepository
import com.sanya1am.consecutivepractices.profile.domain.model.ProfileEntity
import com.sanya1am.consecutivepractices.profile.domain.repository.IProfileRepository
import com.sanya1am.consecutivepractices.profile.presentation.viewModel.EditProfileViewModel
import com.sanya1am.consecutivepractices.profile.presentation.viewModel.ProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val rootModule = module {

    single { getDataStore(androidContext = androidContext()) }

    single<MoviesRepository> { MoviesRepository(get(), get(), get()) }

    factory { MovieResponseToEntityMapper() }

    viewModel { ListViewModel(get(), it.get()) }
    viewModel { DetailsViewModel(get(), it.get(), it.get()) }
    viewModel { FavoritesViewModel(get()) }

    factory<DataStore<ProfileEntity>>(named("profile")) { DataSourceProvider(get()).provide() }

    single<IProfileRepository> { ProfileRepository() }

    viewModel { ProfileViewModel(get()) }

    viewModel { EditProfileViewModel(get()) }
}

fun getDataStore(androidContext: Context): DataStore<Preferences> =
    PreferenceDataStoreFactory.create {
        androidContext.preferencesDataStoreFile("default")
    }