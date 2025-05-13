package com.sanya1am.profile.di

import androidx.datastore.core.DataStore
import com.sanya1am.profile.data.datastore.DataSourceProvider
import com.sanya1am.profile.data.repository.ProfileRepository
import com.sanya1am.profile.domain.model.ProfileEntity
import com.sanya1am.profile.domain.repository.IProfileRepository
import com.sanya1am.profile.presentation.viewModel.EditProfileViewModel
import com.sanya1am.profile.presentation.viewModel.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val profileModule = module {

    single<IProfileRepository> { ProfileRepository() }

    factory<DataStore<ProfileEntity>>(named("profile")) { DataSourceProvider(get()).provide() }

    viewModel { ProfileViewModel(get()) }
    viewModel { EditProfileViewModel(get(), it.get()) }
}