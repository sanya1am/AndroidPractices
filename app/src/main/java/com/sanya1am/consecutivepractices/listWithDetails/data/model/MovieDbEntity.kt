package com.sanya1am.consecutivepractices.listWithDetails.data.model

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Entity
class MovieDbEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    @ColumnInfo(name = "movieName")
    val name: String?,
    @ColumnInfo(name = "movieYear")
    val year: String?,
    @ColumnInfo(name = "movieType")
    val type: String?,
    @ColumnInfo(name = "moviePoster")
    val previewUrl: String?,
)