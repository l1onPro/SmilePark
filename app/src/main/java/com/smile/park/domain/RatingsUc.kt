package com.smile.park.domain

import com.smile.park.domain.models.Rating
import kotlinx.coroutines.flow.Flow
import java.util.*

class RatingsUc(
    private val ratingsRepository: RatingsRepository,
) {

    val ratingsFlow: Flow<List<Rating>>
        get() = ratingsRepository.ratingsFlow

    suspend fun addLike(attractionId: Int) {
        ratingsRepository.new(attractionId, Date().time, true)
    }

    suspend fun addDislike(attractionId: Int) {
        ratingsRepository.new(attractionId, Date().time, false)
    }
}