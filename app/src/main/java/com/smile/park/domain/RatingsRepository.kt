package com.smile.park.domain

import com.smile.park.domain.models.Rating
import kotlinx.coroutines.flow.Flow

interface RatingsRepository {

    val ratingsFlow: Flow<List<Rating>>

    suspend fun new(idAttraction: Int, date: Long, isGood: Boolean)

    suspend fun deleteByAttraction(idAttraction: Int)

}