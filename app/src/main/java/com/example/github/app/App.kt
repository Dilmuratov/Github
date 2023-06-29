package com.example.github.app

import android.app.Application
import com.example.github.di.appModule
import com.example.github.di.dataModule
import com.example.github.di.domainModule
import com.example.github.di.localModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    companion object {
        lateinit var context: App
    }

    override fun onCreate() {
        super.onCreate()
        context = this

        startKoin {
            modules(listOf(appModule, dataModule, domainModule, localModule))
            androidContext(context)
        }
    }
}