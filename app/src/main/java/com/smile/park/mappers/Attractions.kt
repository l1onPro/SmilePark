package com.smile.park.mappers

import com.smile.park.data.models.DatabaseAttraction
import com.smile.park.domain.models.Attraction

fun DatabaseAttraction.toDomain() = Attraction(
    id = this.uid ?: -1,
    title = this.title,
    description = this.description,
)

fun Attraction.toDatabase() = DatabaseAttraction(
    uid = this.id,
    title = this.title,
    description = this.description,
)