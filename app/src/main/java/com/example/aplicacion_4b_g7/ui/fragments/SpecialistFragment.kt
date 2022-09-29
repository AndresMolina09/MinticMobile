package com.example.aplicacion_4b_g7.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacion_4b_g7.ui.adapters.DoctorAdapter
import com.example.aplicacion_4b_g7.data.models.DoctorModel
import com.example.aplicacion_4b_g7.interfaces.OnDoctorClickListener
import com.example.aplicacion_4b_g7.R
import com.example.aplicacion_4b_g7.databinding.FragmentSpecialistBinding

class SpecialistFragment : Fragment() {

    private var _binding: FragmentSpecialistBinding? = null
    private val binding: FragmentSpecialistBinding get() = _binding!!
    private val args: SpecialistFragmentArgs by navArgs()
    private lateinit var doctorAdapter: DoctorAdapter
    private lateinit var doctorList: List<DoctorModel>
    private lateinit var categories: List<String>
    private lateinit var originalList: List<DoctorModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSpecialistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        categories = listOf(
            "Todos","General","Especialista","Odontologia","Dermatologia","Pediatria"
        )
        originalList = listOf(
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
        doctorList = listOf(
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
        if(args.search){
            binding.specialistFragmentSearch.visibility = View.VISIBLE
            binding.specialistFragmentTitleList.visibility = View.GONE
            binding.specialistFragmentTitle.text = getString(R.string.specialist_fragment_title)
            binding.specialistFragmentSubtitle.text = getString(R.string.specialist_fragment_subtitle)
        }else{
            binding.specialistFragmentSearch.visibility = View.GONE
            binding.specialistFragmentTitleList.visibility = View.VISIBLE
            binding.specialistFragmentTitle.text = args.name
            doctorList = originalList.filter {x -> x.speciality == args.name}
            binding.specialistFragmentSubtitle.text = args.description
        }

        doctorAdapter = DoctorAdapter(doctorList as MutableList<DoctorModel>)
        doctorAdapter.listener = object : OnDoctorClickListener {
            override fun onClick(item: DoctorModel) {
                Log.d("Hola", item.name)
            }
        }

        binding.specialistFragmentSearchAutocomplete.setAdapter(
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, categories))
        binding.specialistFragmentSearchAutocomplete.setOnItemClickListener(){parent, view, position, id->
            val category = categories[position]
            if(category != "Todos"){
                doctorList = originalList.filter { x -> x.speciality == category }
            }else{
                doctorList = originalList
            }
            doctorList = originalList.filter { x -> x.speciality == category}
            doctorAdapter.changeDataSet(doctorList)
        }

        binding.homeFragmentRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = doctorAdapter
        }
    }
}

