package com.example.aplicacion_4b_g7.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.aplicacion_4b_g7.ui.activities.HomeActivity
import com.example.aplicacion_4b_g7.R
import com.example.aplicacion_4b_g7.data.viewmodels.LoginViewModel
import com.example.aplicacion_4b_g7.databinding.FragmentLoginBinding
import com.example.aplicacion_4b_g7.isValidEmail
import com.example.aplicacion_4b_g7.isValidPassword
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override  fun onStart(){
        super.onStart()

        binding.fragmentLoginButton.setOnClickListener{
            if(!binding.loginEmail.text.toString().isValidEmail()){
                binding.loginEmailLayout.error = getString(R.string.email_error)
            }else{
                binding.loginEmailLayout.error = null
            }

            if(!binding.loginPassword.text.toString().isValidPassword()){
                binding.loginPasswordLayout.error = getString(R.string.password_error)
            } else {
                binding.loginPasswordLayout.error = null
            }

            if(binding.loginEmail.text.toString().isValidEmail() && binding.loginPassword.text.toString().isValidPassword()){
                loginViewModel.login(binding.loginEmail.text.toString(), binding.loginPassword.text.toString())
                //val intent = Intent(requireContext(), HomeActivity::class.java)
                //startActivity(intent)
                //requireActivity().finish()
            }
        }

        binding.fragmentLoginLabel2.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.loginForgotButton.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_forgotFragment)
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        loginViewModel.login.observe(this, Observer {
            if(it){
                val intent = Intent(requireContext(), HomeActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }else{
                Snackbar.make(binding.root,getString(R.string.login_error), Snackbar.LENGTH_LONG).show()
            }
        })
    }

}