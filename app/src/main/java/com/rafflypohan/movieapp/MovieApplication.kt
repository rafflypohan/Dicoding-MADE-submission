package com.rafflypohan.movieapp

import android.app.Application
import com.rafflypohan.movieapp.core.di.databaseModule
import com.rafflypohan.movieapp.core.di.networkModule
import com.rafflypohan.movieapp.core.di.repositoryModule
import com.rafflypohan.movieapp.di.useCaseModule
import com.rafflypohan.movieapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@MovieApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}