package com.example.aplicacion_4b_g7.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacion_4b_g7.data.models.CompanyModel
import com.example.aplicacion_4b_g7.data.models.DoctorModel
import com.example.aplicacion_4b_g7.data.models.ServiceModel
import com.example.aplicacion_4b_g7.data.repositories.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: HomeRepository): ViewModel() {

    private  var _services: MutableLiveData<List<ServiceModel>> = MutableLiveData()
    val services: LiveData<List<ServiceModel>> get() = _services

    private var _company: MutableLiveData<CompanyModel> = MutableLiveData()
    val company: LiveData<CompanyModel> get() = _company

    private var _doctors: MutableLiveData<List<DoctorModel>> = MutableLiveData()
    val doctors: LiveData<List<DoctorModel>> get() = _doctors

    fun getServices(){
        viewModelScope.launch {
            _services.postValue(repo.getServices())
        }
    }

    fun getInfo(){
        viewModelScope.launch {
            _company.postValue(repo.getInfo())
        }
    }

    fun getSpecialist(category: String?){
        viewModelScope.launch {
            _doctors.postValue(repo.getSpecialist(category))
        }
    }
}