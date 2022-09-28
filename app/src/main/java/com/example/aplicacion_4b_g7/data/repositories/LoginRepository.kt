package com.example.aplicacion_4b_g7.data.repositories

import kotlinx.coroutines.delay
import java.lang.Exception

class LoginRepository {
    suspend fun login(email: String, password: String){
        delay(2000)
        if (email != "juliomellizo24@gmail.com" || password != "123456789"){
            throw Exception("Credenciales invalidas")
        }
    }
}