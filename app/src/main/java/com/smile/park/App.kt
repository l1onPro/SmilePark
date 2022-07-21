package com.smile.park

import android.app.Application
import com.smile.park.di.DatabaseModule
import com.smile.park.di.RepositoryModule
import com.smile.park.di.UseCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)

            modules(
                DatabaseModule.module,
                RepositoryModule.module,
                UseCaseModule.module,
            )
        }
    }
}