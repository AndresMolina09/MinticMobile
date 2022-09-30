package com.example.aplicacion_4b_g7.data.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacion_4b_g7.data.models.UserModel
import com.example.aplicacion_4b_g7.data.repositories.LoginRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel(private val repo: LoginRepository):ViewModel() {
    private var _login: MutableLiveData<Boolean> = MutableLiveData()
    val login: LiveData<Boolean> get() = _login

    private var _signUp: MutableLiveData<Boolean> = MutableLiveData()
    val signUp: LiveData<Boolean> get() = _signUp

    private var _user: MutableLiveData<UserModel?> = MutableLiveData()
    val user: LiveData<UserModel?> get() = _user

    fun login(email: String, password: String){
        viewModelScope.launch {
            try{
                repo.login(email, password)
                _login.postValue(true)
            }catch (e: Exception){
                _login.postValue(false)
            }
        }
    }

    fun signUp(email: String, password: String, name: String, gender: String){
        viewModelScope.launch {
            try{
                repo.signUp(email, password, name, gender)
                _signUp.postValue(true)
            }catch (e: Exception){
                _signUp.postValue(false)
            }
        }
    }

    fun logOut(){
        viewModelScope.launch {
            try{
                repo.logOut()
                _user.postValue(null)
            }catch (e: Exception){
            }
        }
    }

    fun currentUser(){
        viewModelScope.launch {
            _user.postValue(repo.getCurrentUser())
        }
    }

    fun uploadImage(image: Bitmap){
        viewModelScope.launch {
            _user.postValue(repo.uploadImage(image))
        }
    }
}