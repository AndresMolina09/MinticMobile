package com.example.aplicacion_4b_g7.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacion_4b_g7.ui.adapters.DoctorAdapter
import com.example.aplicacion_4b_g7.data.models.DoctorModel
import com.example.aplicacion_4b_g7.interfaces.OnDoctorClickListener
import com.example.aplicacion_4b_g7.R
import com.example.aplicacion_4b_g7.data.viewmodels.HomeViewModel
import com.example.aplicacion_4b_g7.databinding.FragmentSpecialistBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SpecialistFragment : Fragment() {

    private var _binding: FragmentSpecialistBinding? = null
    private val binding: FragmentSpecialistBinding get() = _binding!!
    private val args: SpecialistFragmentArgs by navArgs()
    private lateinit var doctorAdapter: DoctorAdapter
    private lateinit var categories: MutableList<String>
    private val homeViewModel: HomeViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSpecialistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if(args.search){
            homeViewModel.getSpecialist(null)
            binding.specialistFragmentSearch.visibility = View.VISIBLE
            binding.specialistFragmentTitleList.visibility = View.GONE
            binding.specialistFragmentTitle.text = getString(R.string.specialist_fragment_title)
            binding.specialistFragmentSubtitle.text = getString(R.string.specialist_fragment_subtitle)
        }else{
            binding.specialistFragmentSearch.visibility = View.GONE
            binding.specialistFragmentTitleList.visibility = View.VISIBLE
            binding.specialistFragmentTitle.text = args.name
            homeViewModel.getSpecialist(args.name)
            binding.specialistFragmentSubtitle.text = args.description
        }

        doctorAdapter = DoctorAdapter(mutableListOf())
        doctorAdapter.listener = object : OnDoctorClickListener {
            override fun onClick(item: DoctorModel) {
                Log.d("Hola", item.name)
            }
        }

        binding.homeFragmentRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = doctorAdapter
        }

        observeViewModels()
    }

    private fun observeViewModels(){
        homeViewModel.doctors.observe(viewLifecycleOwner, Observer {
            doctorAdapter.changeDataSet(it)
        })
        homeViewModel.services.observe(viewLifecycleOwner, Observer {
            categories = mutableListOf(
                "Todos"
            )
            it.forEach{
                categories.add(it.title)
            }
            binding.specialistFragmentSearchAutocomplete.setAdapter(
                ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, categories))
            binding.specialistFragmentSearchAutocomplete.setOnItemClickListener(){parent, view, position, id->
                val category = categories[position]
                if(category != "Todos")
                    homeViewModel.getSpecialist(category)
                else
                    homeViewModel.getSpecialist(null)
            }
        })
    }
}

