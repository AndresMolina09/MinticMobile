package com.example.aplicacion_4b_g7.data.repositories

import com.example.aplicacion_4b_g7.data.datasources.MemoryDataSource
import com.example.aplicacion_4b_g7.data.models.UserModel
import kotlinx.coroutines.delay
import java.lang.Exception

class LoginRepository(private val memoryDataSource: MemoryDataSource) {
    suspend fun login(email: String, password: String){
        delay(2000)
        if (email != "juliomellizo24@gmail.com" || password != "123456789"){
            throw Exception("Credenciales invalidas")
        }
    }

    suspend fun logOut(){

    }

    suspend fun getCurrentUser(): UserModel{
        return memoryDataSource.getCurrentUser()
    }
}