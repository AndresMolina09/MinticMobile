package com.example.aplicacion_4b_g7.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacion_4b_g7.interfaces.OnServiceClickListener
import com.example.aplicacion_4b_g7.R
import com.example.aplicacion_4b_g7.ui.adapters.ServiceAdapter
import com.example.aplicacion_4b_g7.data.models.ServiceModel
import com.example.aplicacion_4b_g7.data.viewmodels.HomeViewModel
import com.example.aplicacion_4b_g7.data.viewmodels.LoginViewModel
import com.example.aplicacion_4b_g7.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private lateinit var serviceAdapter: ServiceAdapter
    private val homeViewModel: HomeViewModel by sharedViewModel()
    private val loginViewModel: LoginViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        loginViewModel.currentUser()
        homeViewModel.getServices()
        serviceAdapter = ServiceAdapter(
            mutableListOf()
        )

        serviceAdapter.listener = object : OnServiceClickListener {
            override fun onClick(item: ServiceModel) {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToSpecialistFragment2()
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
        observeViewModels()
    }

    private fun observeViewModels(){
        homeViewModel.services.observe(viewLifecycleOwner, Observer {
            serviceAdapter.changeDataSet(it)
        })
    }

}