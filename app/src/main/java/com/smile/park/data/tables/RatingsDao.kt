package com.smile.park.data.tables

import androidx.room.*
import com.smile.park.data.models.DatabaseRating
import kotlinx.coroutines.flow.Flow

@Dao
interface RatingsDao {
    @Query("SELECT * from ratingsTable")
    fun getAllFlow(): Flow<List<DatabaseRating>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: DatabaseRating)

    @Query("DELETE from ratingsTable")
    fun deleteAll()

    @Query("Delete FROM attractionsTable WHERE 'attraction_id' = :attractionId ")
    fun deleteByAttractionId(attractionId: Int)
}