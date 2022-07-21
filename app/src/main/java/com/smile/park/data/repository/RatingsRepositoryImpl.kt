package com.smile.park.data.repository

import com.smile.park.data.AppDatabase
import com.smile.park.data.models.DatabaseRating
import com.smile.park.domain.RatingsRepository
import com.smile.park.domain.models.Rating
import com.smile.park.mappers.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RatingsRepositoryImpl(
    private val roomStorage: AppDatabase,
) : RatingsRepository {

    override val ratingsFlow: Flow<List<Rating>>
        get() = roomStorage.ratingsDao().getAllFlow().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun new(idAttraction: Int, date: Long, isGood: Boolean) {
        roomStorage.ratingsDao().insert(
            DatabaseRating(
                isGood = isGood,
                dateTimeStamp = date,
                attractionId = idAttraction,
            )
        )
    }

    override suspend fun deleteByAttraction(idAttraction: Int) {
        roomStorage.ratingsDao().deleteByAttractionId(idAttraction)
    }

}