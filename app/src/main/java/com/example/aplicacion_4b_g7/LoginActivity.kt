package com.example.aplicacion_4b_g7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aplicacion_4b_g7.databinding.ActivityLoginBinding
import com.example.aplicacion_4b_g7.databinding.ActivitySplashBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}