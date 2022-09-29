package com.example.aplicacion_4b_g7.data.viewmodels

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

    private var _logOut: MutableLiveData<Boolean> = MutableLiveData()
    val logOut: LiveData<Boolean> get() = _logOut

    private var _user: MutableLiveData<UserModel> = MutableLiveData()
    val user: LiveData<UserModel> get() = _user

    fun login(email: String, password: String){
        //_login.postValue()
        viewModelScope.launch {
            try{
                repo.login(email, password)
                _login.postValue(true)
            }catch (e: Exception){
                _login.postValue(false)
            }
        }
    }

    fun logOut(){
        viewModelScope.launch {
            try{
                repo.logOut()
                _logOut.postValue(true)
            }catch (e: Exception){
                _logOut.postValue(false)
            }
        }
    }

    fun currentUser(){
        viewModelScope.launch {
            _user.postValue(repo.getCurrentUser())
        }
    }
}