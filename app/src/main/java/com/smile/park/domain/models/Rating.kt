package com.smile.park.domain.models

data class Rating(
    val id: Int,
    val isGood: Boolean,
    val dateTimeStamp: Long,
    val attractionId: Int,
)