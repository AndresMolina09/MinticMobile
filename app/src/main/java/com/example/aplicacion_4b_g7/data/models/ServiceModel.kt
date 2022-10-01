package com.example.aplicacion_4b_g7.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "services")
data class ServiceModel (
    @PrimaryKey
    var id: String = "",
    var icon: String = "",
    var title: String = "",
    var description: String = ""
)