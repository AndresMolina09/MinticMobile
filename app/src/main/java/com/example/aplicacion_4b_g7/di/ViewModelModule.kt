package com.example.aplicacion_4b_g7.di

import com.example.aplicacion_4b_g7.data.repositories.HomeRepository
import com.example.aplicacion_4b_g7.data.viewmodels.HomeViewModel
import com.example.aplicacion_4b_g7.data.viewmodels.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        LoginViewModel(get())
    }
    viewModel{
        HomeViewModel(get())
    }
}