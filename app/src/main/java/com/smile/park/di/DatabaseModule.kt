package com.smile.park.di

import com.smile.park.data.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DatabaseModule {
    val module = module {
        single { AppDatabase.provideDatabase(androidContext()) }
    }
}