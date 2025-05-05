package com.sanya1am.consecutivepractices.profile.domain.repository

import com.sanya1am.consecutivepractices.profile.domain.model.ProfileEntity
import kotlinx.coroutines.flow.Flow

interface IProfileRepository {
    suspend fun getProfile(): ProfileEntity?
    suspend fun setProfile(name: String, photoUri: String, documentUrl: String): ProfileEntity
    suspend fun observeProfile(): Flow<ProfileEntity>
}