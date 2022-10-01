package com.example.aplicacion_4b_g7.data.repositories

import com.example.aplicacion_4b_g7.COMPANY_COLLECTION
import com.example.aplicacion_4b_g7.DETAILS_COLLECTION
import com.example.aplicacion_4b_g7.DOCTOR_COLLECTION
import com.example.aplicacion_4b_g7.SERVICE_COLLECTION
import com.example.aplicacion_4b_g7.data.dao.DoctorDao
import com.example.aplicacion_4b_g7.data.dao.ServiceDao
import com.example.aplicacion_4b_g7.data.datasources.MemoryDataSource
import com.example.aplicacion_4b_g7.data.models.CompanyModel
import com.example.aplicacion_4b_g7.data.models.DoctorDetailModel
import com.example.aplicacion_4b_g7.data.models.DoctorModel
import com.example.aplicacion_4b_g7.data.models.ServiceModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class HomeRepository(private val memoryDataSource: MemoryDataSource,
                     private val serviceDao: ServiceDao,
                     private val doctorDao: DoctorDao,
                     private val db: FirebaseFirestore,
                     private val storage: FirebaseStorage,
                     private val auth: FirebaseAuth) {

    suspend fun  getServices(): List<ServiceModel>{
        //return  memoryDataSource.getServices()
        //return serviceDao.getAll()
        val result = (db.collection(SERVICE_COLLECTION).get().await()).toObjects<ServiceModel>()
        return result.map {
            val ref = storage.reference
            val imageRef = ref.child(it.icon)
            it.icon = imageRef.downloadUrl.await().toString()
            return@map it
        }
    }

    suspend fun getInfo(): CompanyModel{
        //return memoryDataSource.getInfo()
        val result = db.collection(COMPANY_COLLECTION).get().await()
        return result.first().toObject<CompanyModel>()
    }

    suspend fun getSpecialist(category: String?): List<DoctorModel>{
        //if(category == null) return doctorDao.getAll()
        //return doctorDao.getAllBySpeciality(category)
        var result: List<DoctorModel>
        if(category != null){
            result = (db.collection(DOCTOR_COLLECTION).whereEqualTo("speciality", category).get().await()).toObjects()
        }else{
            result = db.collection(DOCTOR_COLLECTION).get().await().toObjects()
        }
        return result.map {
            val ref = storage.reference
            val imageRef = ref.child(it.image)
            it.image = imageRef.downloadUrl.await().toString()
            return@map it
        }
    }

    suspend fun getDetails(id: String): DoctorDetailModel?{
        return db.collection(DETAILS_COLLECTION).document(id).get().await().toObject()
    }
}