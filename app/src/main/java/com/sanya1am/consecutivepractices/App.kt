package com.sanya1am.consecutivepractices

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sanya1am.impl.di.dbModule
import com.sanya1am.consecutivepractices.di.rootModule
import com.sanya1am.consecutivepractices.di.networkModule
import com.sanya1am.favorites.di.favoritesModule
import com.sanya1am.impl.di.moviesModule
import com.sanya1am.profile.di.profileModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(rootModule, moviesModule, profileModule, favoritesModule, networkModule, dbModule)
        }
    }
}