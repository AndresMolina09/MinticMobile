package com.example.aplicacion_4b_g7.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.aplicacion_4b_g7.data.models.DoctorModel

@Dao
interface DoctorDao {

    @Query("SELECT * FROM doctors")
    suspend fun getAll(): List<DoctorModel>

    @Query("SELECT * FROM doctors WHERE speciality = :speciality")
    suspend fun getAllBySpeciality(speciality: String): List<DoctorModel>

    @Query("SELECT COUNT(*) FROM doctors")
    suspend fun count(): Int

    @Insert
    suspend fun insertAll(service: List<DoctorModel>)

}