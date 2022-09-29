package com.example.aplicacion_4b_g7.data.repositories

import com.example.aplicacion_4b_g7.data.datasources.MemoryDataSource
import com.example.aplicacion_4b_g7.data.models.ServiceModel

class HomeRepository(private val memoryDataSource: MemoryDataSource) {

    suspend fun  getServices(): List<ServiceModel>{
        return  memoryDataSource.getServices()
    }
}