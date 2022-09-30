package com.example.aplicacion_4b_g7.data.repositories

import com.example.aplicacion_4b_g7.data.dao.DoctorDao
import com.example.aplicacion_4b_g7.data.dao.ServiceDao
import com.example.aplicacion_4b_g7.data.datasources.MemoryDataSource
import com.example.aplicacion_4b_g7.data.models.CompanyModel
import com.example.aplicacion_4b_g7.data.models.DoctorModel
import com.example.aplicacion_4b_g7.data.models.ServiceModel

class HomeRepository(private val memoryDataSource: MemoryDataSource,
                     private val serviceDao: ServiceDao, private val doctorDao: DoctorDao) {

    suspend fun  getServices(): List<ServiceModel>{
        //return  memoryDataSource.getServices()
        return serviceDao.getAll()
    }

    suspend fun getInfo(): CompanyModel{
        return memoryDataSource.getInfo()
    }

    suspend fun getSpecialist(category: String?): List<DoctorModel>{
        if(category == null) return doctorDao.getAll()
        return doctorDao.getAllBySpeciality(category)
    }
}