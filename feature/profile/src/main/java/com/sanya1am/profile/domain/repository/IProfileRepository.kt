package com.sanya1am.profile.domain.repository

import com.sanya1am.profile.domain.model.ProfileEntity
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

interface IProfileRepository {
    suspend fun getProfile(): ProfileEntity?
    suspend fun setProfile(name: String, photoUri: String, documentUrl: String, notifTime: LocalTime, notifDate: LocalDate): ProfileEntity
    suspend fun observeProfile(): Flow<ProfileEntity>
}