package com.example.aplicacion_4b_g7.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "services")
data class ServiceModel (
    @PrimaryKey
    val id: String,
    val icon: String,
    val title: String,
    val description: String
)