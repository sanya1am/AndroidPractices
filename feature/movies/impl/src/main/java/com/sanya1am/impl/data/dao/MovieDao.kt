package com.sanya1am.impl.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sanya1am.impl.data.model.MovieDbEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieDbEntity")
    suspend fun getAll(): List<MovieDbEntity>

    @Insert
    suspend fun insert(driverDbEntity: MovieDbEntity)
}