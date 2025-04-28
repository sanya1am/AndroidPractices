package com.sanya1am.consecutivepractices.profile.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.sanya1am.consecutivepractices.profile.domain.model.ProfileEntity

class DataSourceProvider(val context: Context) {
    private val Context.profileDataStore: DataStore<ProfileEntity> by dataStore(
        fileName = "profile.pb",
        serializer = ProfileSerializer
    )

    fun provide() = context.profileDataStore
}