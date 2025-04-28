package com.sanya1am.consecutivepractices.profile.data.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.sanya1am.consecutivepractices.profile.domain.model.ProfileEntity
import java.io.InputStream
import java.io.OutputStream

object ProfileSerializer : Serializer<ProfileEntity> {
    override val defaultValue: ProfileEntity
        get() = ProfileEntity.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ProfileEntity {
        try {
            return ProfileEntity.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read photo", e)
        }
    }

    override suspend fun writeTo(t: ProfileEntity, output: OutputStream) {
        t.writeTo(output)
    }
}