package com.smile.park.domain

import com.smile.park.domain.models.Attraction
import kotlinx.coroutines.flow.Flow

interface AttractionsRepository {

    val allAttractionsFlow: Flow<List<Attraction>>

    suspend fun createNew(title: String, description: String, time: Long)

    suspend fun update(attraction: Attraction)

    suspend fun getById(id: Int): Attraction

    suspend fun deleteById(id: Int)

}