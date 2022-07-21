package com.smile.park.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.smile.park.data.models.DatabaseAttraction
import com.smile.park.data.models.DatabaseRating
import com.smile.park.data.tables.AttractionDao
import com.smile.park.data.tables.RatingsDao

@Database(
    entities = [
        DatabaseAttraction::class,
        DatabaseRating::class
    ], version = 1
)

abstract class AppDatabase : RoomDatabase() {

    companion object {

        fun provideDatabase(context: Context): AppDatabase {
            return Room
                .databaseBuilder(context, AppDatabase::class.java, "smile_park_db")
                .build()
        }
    }

    abstract fun attractionsDao(): AttractionDao

    abstract fun ratingsDao(): RatingsDao
}