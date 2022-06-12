package com.example.android.movies

import android.app.Application
import com.example.android.movies.di.*
import com.movies.core.data.api.networkModule
import org.koin.core.context.startKoin

class KApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(networkModule, dispatcherModule, datasourceModule, interactorModule, repoModule, viewModelModule))
        }
    }
}