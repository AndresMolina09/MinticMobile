package com.example.aplicacion_4b_g7

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacion_4b_g7.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private lateinit var serviceAdapter: ServiceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        serviceAdapter = ServiceAdapter(
            listOf(
                ServiceModel(
                    "1", R.drawable.ico_general.toString(),
                    "General","Los mejores medicos generales"

                ),
                ServiceModel(
                    "2",R.drawable.ico_especialidad.toString(),
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
        )

        serviceAdapter.listener = object : OnServiceClickListener{
            override fun onClick(item: ServiceModel) {
                val action = HomeFragmentDirections.actionHomeFragmentToSpecialistFragment2()
                action.search = false
                action.name = item.title
                action.description = item.description
                findNavController().navigate(action)
            }

        }
        binding.homeFragmentRecycler.apply {
            adapter = serviceAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

}