package com.example.aplicacion_4b_g7.di

import com.example.aplicacion_4b_g7.data.datasources.MemoryDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { MemoryDataSource() }
}