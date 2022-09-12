package com.example.aplicacion_4b_g7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aplicacion_4b_g7.databinding.FragmentSpecialistBinding

class SpecialistFragment : Fragment() {

    private var _binding: FragmentSpecialistBinding? = null
    private val binding: FragmentSpecialistBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSpecialistBinding.inflate(inflater, container, false)
        return binding.root
    }

}