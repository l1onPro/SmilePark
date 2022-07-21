package com.smile.park.data.repository

import com.smile.park.data.AppDatabase
import com.smile.park.data.models.DatabaseAttraction
import com.smile.park.domain.AttractionsRepository
import com.smile.park.domain.models.Attraction
import com.smile.park.mappers.toDatabase
import com.smile.park.mappers.toDomain
import kotlinx.coroutines.flow.map

class AttractionsRepositoryImpl(
    private val roomStorage: AppDatabase,
) : AttractionsRepository {

    override val allAttractionsFlow
        get() = roomStorage.attractionsDao().getAllFlow().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun createNew(title: String, description: String, time: Long) {
        roomStorage.attractionsDao().insert(
            DatabaseAttraction(
                title = title,
                description = description,
            )
        )
    }

    override suspend fun update(attraction: Attraction) {
        roomStorage.attractionsDao().insert(attraction.toDatabase())
    }

    override suspend fun getById(id: Int): Attraction {
        return roomStorage.attractionsDao().getById(id).toDomain()
    }

    override suspend fun deleteById(id: Int) {
        roomStorage.attractionsDao().deleteById(id)
    }
}