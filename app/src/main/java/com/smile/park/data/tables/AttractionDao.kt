package com.smile.park.data.tables

import androidx.room.*
import com.smile.park.data.models.DatabaseAttraction
import kotlinx.coroutines.flow.Flow

@Dao
interface AttractionDao {

    @Query("SELECT * from attractionsTable")
    fun getAllFlow(): Flow<List<DatabaseAttraction>>

    @Query("SELECT * FROM attractionsTable WHERE uid = :attractionId ")
    fun getById(attractionId: Int): DatabaseAttraction

    @Query("Delete FROM attractionsTable WHERE uid = :attractionId ")
    fun deleteById(attractionId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<DatabaseAttraction>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: DatabaseAttraction)

    @Update
    fun update(item: DatabaseAttraction)

    @Query("DELETE from attractionsTable")
    fun deleteAll()
}