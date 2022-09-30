package com.example.aplicacion_4b_g7.di

import androidx.room.Room
import com.example.aplicacion_4b_g7.data.AppDatabase
import com.example.aplicacion_4b_g7.data.dao.DoctorDao
import com.example.aplicacion_4b_g7.data.dao.ServiceDao
import com.example.aplicacion_4b_g7.data.datasources.MemoryDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataSourceModule = module {
    single { MemoryDataSource() }
    single<AppDatabase> {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "dbmintic").build()
    }
    single<DoctorDao>{
        get<AppDatabase>().doctorDao()
    }
    single<ServiceDao>{
        get<AppDatabase>().serviceDao()
    }
}