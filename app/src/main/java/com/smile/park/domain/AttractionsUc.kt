package com.smile.park.domain

import com.smile.park.domain.models.Attraction
import kotlinx.coroutines.flow.Flow
import java.util.*

class AttractionsUc(
    private val attractionsRepository: AttractionsRepository,
    private val ratingsRepository: RatingsRepository
) {

    val attractions: Flow<List<Attraction>>
        get() = attractionsRepository.allAttractionsFlow

    suspend fun addNew(title: String, description: String) {
        attractionsRepository.createNew(title, description, Date().time)
    }

    suspend fun update(attraction: Attraction) {
        attractionsRepository.update(attraction)
    }

    suspend fun deleteById(id: Int) {
        attractionsRepository.deleteById(id)
        ratingsRepository.deleteByAttraction(id)
    }
}