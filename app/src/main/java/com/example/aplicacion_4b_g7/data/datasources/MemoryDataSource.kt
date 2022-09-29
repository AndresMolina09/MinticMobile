package com.example.aplicacion_4b_g7.data.datasources

import com.example.aplicacion_4b_g7.R
import com.example.aplicacion_4b_g7.data.models.ServiceModel

class MemoryDataSource {

    suspend fun getServices(): List<ServiceModel>{
        return listOf(
            ServiceModel(
                "1", R.drawable.ico_general.toString(),
                "General","Los mejores medicos generales"

            ),
            ServiceModel(
                "2", R.drawable.ico_especialidad.toString(),
                "Especialista","Los mejores medicos especialistas",

                ),
            ServiceModel(
                "3", R.drawable.ico_odontologia.toString(),
                "Odontologia", "Los mejores especialistas en odontologia",

                ),
            ServiceModel(
                "4", R.drawable.ico_dermatologia.toString(),
                "Dermatologia", "Los mejores especialistas en dermatologia",

                ),
            ServiceModel(
                "5", R.drawable.ico_pediatria.toString(),
                "Pediatria", "Los mejores especialistas en pediatria",

                )
        )
    }
}