package com.sanya1am.consecutivepractices.profile.data.repository

import androidx.datastore.core.DataStore
import com.sanya1am.consecutivepractices.profile.domain.model.ProfileEntity
import com.sanya1am.consecutivepractices.profile.domain.repository.IProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.inject
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

class ProfileRepository : IProfileRepository {
    private val dataStore: DataStore<ProfileEntity> by inject(DataStore::class.java, named("profile"))

    override suspend fun getProfile(): ProfileEntity? = dataStore.data.firstOrNull()

    override suspend fun setProfile(name: String, photoUri: String, documentUrl: String, notifTime: LocalTime, notifDate: LocalDate): ProfileEntity =
        dataStore.updateData {
            it.toBuilder().apply {
                this.name = name
                this.photoUri = photoUri
                this.documentUrl = documentUrl
                this.notifTime = notifTime.toString()
                this.notifDate = notifDate.toString()
            }.build()
        }

    override suspend fun observeProfile(): Flow<ProfileEntity> = dataStore.data
}