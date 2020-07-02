package com.example.android.movies

import android.app.Application
import com.example.android.movies.injection.repoModule
import com.example.android.movies.injection.viewModelModule
import org.koin.core.context.startKoin

class KApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(repoModule, viewModelModule))
        }
    }
}