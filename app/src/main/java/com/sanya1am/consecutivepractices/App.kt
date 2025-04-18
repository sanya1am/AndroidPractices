package com.sanya1am.consecutivepractices

import android.app.Application
import com.sanya1am.consecutivepractices.di.rootModule
import com.sanya1am.consecutivepractices.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(rootModule, networkModule)
        }
    }
}