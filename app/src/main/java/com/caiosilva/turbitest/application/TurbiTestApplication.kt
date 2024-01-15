package com.caiosilva.turbitest.application

import android.app.Application
import com.caiosilva.turbitest.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class TurbiTestApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TurbiTestApplication)
            modules(Modules.module)
        }
    }
}