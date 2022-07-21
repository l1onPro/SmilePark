package com.smile.park.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ratingsTable")
data class DatabaseRating(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "is_good") val isGood: Boolean,
    @ColumnInfo(name = "timestamp") val dateTimeStamp: Long,
    @ColumnInfo(name = "attraction_id") val attractionId: Int,
)