package com.example.aplicacion_4b_g7

import android.app.Application
import com.example.aplicacion_4b_g7.di.dataSourceModule
import com.example.aplicacion_4b_g7.di.repoModule
import com.example.aplicacion_4b_g7.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MinticApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@MinticApp)
            modules(dataSourceModule, repoModule, viewModelModule)
        }
    }
}