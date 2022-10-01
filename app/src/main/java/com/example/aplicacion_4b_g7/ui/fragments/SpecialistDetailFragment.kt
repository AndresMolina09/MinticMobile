package com.example.aplicacion_4b_g7.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.aplicacion_4b_g7.R
import com.example.aplicacion_4b_g7.data.viewmodels.HomeViewModel
import com.example.aplicacion_4b_g7.databinding.FragmentLocationBinding
import com.example.aplicacion_4b_g7.databinding.FragmentSpecialistDetailBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SpecialistDetailFragment : Fragment() {

    private var _binding: FragmentSpecialistDetailBinding? = null
    private val binding: FragmentSpecialistDetailBinding get() = _binding!!
    private val homeViewModel: HomeViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSpecialistDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        observerViewModels()
    }

    private fun observerViewModels(){
        homeViewModel.doctor.observe(viewLifecycleOwner, Observer {
            homeViewModel.getDetail(it.id)
            binding.specialistDetailFragmentName.text = it.name
            binding.specialistDetailFragmentSpeciality.text = it.speciality
            binding.specialistDetailFragmentRating.rating = it.star.toFloat()
            //binding.specialistDetailFragmentImage.setImageResource(it.image.toInt())
            Glide.with(binding.root).load(it.image).centerCrop().into(binding.specialistDetailFragmentImage)
            binding.specialistDetailFragmentDescription.text = it.description
        })
        homeViewModel.detail.observe(viewLifecycleOwner, Observer {
            if(it != null){
                binding.specialistDetailFragmentCardPatientsNumber.text = it.patients
                binding.specialistDetailFragmentCardQualificationNumber.text = it.star.toString()
                binding.specialistDetailFragmentCardExpNumber.text = it.exp
                binding.specialistDetailCardGroup.visibility = View.VISIBLE
            }else{
                binding.specialistDetailCardGroup.visibility = View.GONE
            }
        })
    }
}