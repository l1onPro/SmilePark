package com.smile.park.mappers

import com.smile.park.data.models.DatabaseRating
import com.smile.park.domain.models.Rating

fun DatabaseRating.toDomain() = Rating(
    id = this.uid ?: -1,
    isGood = this.isGood,
    dateTimeStamp = this.dateTimeStamp,
    attractionId = this.attractionId
)

fun Rating.toDatabase() = DatabaseRating(
    uid = this.id,
    isGood = this.isGood,
    dateTimeStamp = this.dateTimeStamp,
    attractionId = this.attractionId
)