package com.example.aplicacion_4b_g7.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctors")
data class DoctorModel (
    @PrimaryKey
    var id: String = "",
    var speciality: String = "",
    var name: String = "",
    var caption: String = "",
    var image: String = "",
    var star: Double = 0.0,
    var description: String = ""
)