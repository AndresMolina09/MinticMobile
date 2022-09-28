package com.example.aplicacion_4b_g7.di

import com.example.aplicacion_4b_g7.data.repositories.LoginRepository
import org.koin.dsl.module

val repoModule = module {
    single { LoginRepository() }
}