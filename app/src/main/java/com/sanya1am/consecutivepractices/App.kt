package com.sanya1am.consecutivepractices

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sanya1am.consecutivepractices.di.dbModule
import com.sanya1am.consecutivepractices.di.rootModule
import com.sanya1am.consecutivepractices.di.networkModule
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
            modules(rootModule, networkModule, dbModule)
        }
    }
}