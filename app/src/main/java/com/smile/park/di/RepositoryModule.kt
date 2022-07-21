package com.smile.park.di

import com.smile.park.data.repository.AttractionsRepositoryImpl
import com.smile.park.data.repository.RatingsRepositoryImpl
import com.smile.park.domain.AttractionsRepository
import com.smile.park.domain.RatingsRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object RepositoryModule {
    val module = module {
        singleOf(::AttractionsRepositoryImpl) { bind<AttractionsRepository>() }
        singleOf(::RatingsRepositoryImpl) { bind<RatingsRepository>() }
    }
}