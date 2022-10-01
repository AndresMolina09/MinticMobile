package com.example.aplicacion_4b_g7.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacion_4b_g7.data.models.CompanyModel
import com.example.aplicacion_4b_g7.data.models.DoctorDetailModel
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

    private var _doctor: MutableLiveData<DoctorModel> = MutableLiveData()
    val doctor: LiveData<DoctorModel> get() = _doctor

    private var _details: MutableLiveData<DoctorDetailModel> = MutableLiveData()
    val detail: LiveData<DoctorDetailModel> = _details

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

    fun selectedDoctor(item: DoctorModel){
        _doctor.postValue(item)
    }

    fun getDetail(id: String){
        viewModelScope.launch {
            _details.postValue(repo.getDetails(id))
        }
    }
}