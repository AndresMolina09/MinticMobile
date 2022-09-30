package com.example.aplicacion_4b_g7.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctors")
data class DoctorModel (
    @PrimaryKey
    val id: String,
    val speciality: String,
    val name: String,
    val caption: String,
    val image: String,
    val star: Double,
    val description: String
)