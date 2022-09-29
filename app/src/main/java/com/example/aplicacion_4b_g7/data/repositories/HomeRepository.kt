package com.example.aplicacion_4b_g7.data.repositories

import com.example.aplicacion_4b_g7.data.datasources.MemoryDataSource
import com.example.aplicacion_4b_g7.data.models.CompanyModel
import com.example.aplicacion_4b_g7.data.models.DoctorModel
import com.example.aplicacion_4b_g7.data.models.ServiceModel

class HomeRepository(private val memoryDataSource: MemoryDataSource) {

    suspend fun  getServices(): List<ServiceModel>{
        return  memoryDataSource.getServices()
    }

    suspend fun getInfo(): CompanyModel{
        return memoryDataSource.getInfo()
    }

    suspend fun getSpecialist(category: String?): List<DoctorModel>{
        return memoryDataSource.getSpecialist(category)
    }
}