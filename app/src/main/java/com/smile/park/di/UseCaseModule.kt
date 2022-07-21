package com.smile.park.di

import com.smile.park.domain.AttractionsUc
import com.smile.park.domain.RatingsUc
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object UseCaseModule {
    val module = module {
        singleOf(::AttractionsUc)
        singleOf(::RatingsUc)
    }
}