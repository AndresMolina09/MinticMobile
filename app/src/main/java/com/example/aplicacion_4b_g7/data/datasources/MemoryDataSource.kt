package com.example.aplicacion_4b_g7.data.datasources

import com.example.aplicacion_4b_g7.R
import com.example.aplicacion_4b_g7.data.models.CompanyModel
import com.example.aplicacion_4b_g7.data.models.DoctorModel
import com.example.aplicacion_4b_g7.data.models.ServiceModel
import com.example.aplicacion_4b_g7.data.models.UserModel

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

    suspend fun getSpecialist(category: String?): List<DoctorModel>{
        val list = listOf(
            DoctorModel("1", "General", "Jhon Zuñiga", "350+ pacientes", R.drawable.segundo_doctor.toString(),
                5.0, "Medico general, graduado de la Universidad del Cauca"),
            DoctorModel("2", "Odontologia", "Danny Mage", "300+ pacientes", R.drawable.primer_doctor.toString(),
                4.0, "Medico odontologo, graduado de la Universidad del Cauca"),
            DoctorModel("3", "Pediatria", "Cristian Pinto", "200+ pacientes", R.drawable.tercer_doctor.toString(),
                4.5, "Medico pediatra, graduado de la Universidad del Cauca"),
            DoctorModel("4", "Especialista", "Nicolas Santander", "400+ pacientes", R.drawable.cuarto_doctor.toString(),
                5.0, "Medico especialista, graduado de la Universidad del Cauca"),
            DoctorModel("5", "Especialista", "Nicolas Santander", "100+ pacientes", R.drawable.quinto_doctor.toString(),
                3.9, "Medico especialista, graduado de la Universidad del Cauca"),
            DoctorModel("6", "Pediatria", "Martha Mellizo", "50+ pacientes", R.drawable.sexta_doctora.toString(),
                3.5, "Medico pediatra, graduado de la Universidad del Cauca"),
            DoctorModel("7", "Dermatologia", "Sandra Mellizo", "200+ pacientes", R.drawable.septima_doctora.toString(),
                4.5, "Medico dermatologo, graduado de la Universidad del Cauca"),
            DoctorModel("8", "Dermatologia", "Laura Chaparro", "150+ pacientes", R.drawable.octava_doctora.toString(),
                5.0, "Medico dermatologo, graduado de la Universidad del Cauca"),
        )
        if(category != null){
            return list.filter { c -> c.speciality == category }
        }
        return list
    }

    suspend fun getInfo(): CompanyModel{
        return CompanyModel(
            "1",
            "MyMedic",
            2.443377779866151,
            -76.60837714470695
        )
    }

    suspend fun getCurrentUser(): UserModel{
        return UserModel(
            "1",
            "Julio Mellizo",
            "juliomellizo24@gmail.com",
            "Masculino",
            "https://www.dmarge.com/wp-content/uploads/2021/01/dwayne-the-rock-.jpg"
        )
    }
}