package com.sanya1am.consecutivepractices.listWithDetails.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanya1am.consecutivepractices.listWithDetails.data.dao.MovieDao
import com.sanya1am.consecutivepractices.listWithDetails.data.model.MovieDbEntity

@Database(entities = [MovieDbEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}